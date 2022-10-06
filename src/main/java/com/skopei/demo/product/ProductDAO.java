package com.skopei.demo.product;

import com.skopei.demo.abstraction.ICRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductDAO")
@RequiredArgsConstructor
public class ProductDAO implements ICRUD<Product> {

    private final JdbcTemplate jdbcTemplate;
    //maps database column to POJO. column index is ignored but required arg for interface
    private final RowMapper<Product> productRowMapper = (resultSet, i) ->
            Product.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .quantity(resultSet.getInt("quantity"))
                    .price(resultSet.getLong("price_euro"))
                    .creationDate(resultSet.getLong("date_created"))
                    .modDate(resultSet.getLong("date_modified"))
                    .deleted(resultSet.getBoolean("deleted"))
                    .build();

    @Override
    public void create(Product product) throws DataAccessException {
        String sql = """
                    INSERT INTO product(
                        name, quantity, price_euro, date_modified, date_created
                    ) VALUES (?,?,?,?,?);
                """;

        long currentTime = System.currentTimeMillis(); // unix timestamp is compliant with UTC time and easy to work with
        jdbcTemplate.update(sql, product.getName(), product.getQuantity(), product.getPrice(), currentTime, currentTime);
    }

    @Override
    public Product read(int id) throws DataAccessException {
        String sql = """
                    SELECT * FROM product
                    WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql, productRowMapper, id);
    }

    @Override
    public List<Product> readList() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    @Override
    public void update(Product product) throws DataAccessException {
        String sql = """
                    UPDATE product
                    SET name=?, quantity=?, price_euro=?, date_modified=?
                    WHERE id = ?;
                """;
        jdbcTemplate.update(sql, product.getName(), product.getQuantity(), product.getPrice(), System.currentTimeMillis(), product.getId());
    }

    @Override
    public void delete(int id) throws DataAccessException {
        String sql = """
                    UPDATE product
                    SET deleted = true,
                        date_modified=?
                    WHERE id = ?;
                """;
        jdbcTemplate.update(sql, System.currentTimeMillis(), id);
    }
}
