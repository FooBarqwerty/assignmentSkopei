package com.skopei.demo.product;

import com.skopei.demo.abstraction.ICRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDAO implements ICRUD<Product> {

    private final JdbcTemplate jdbcTemplate;
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
        long currentTime = System.currentTimeMillis();

        jdbcTemplate.update("""
            INSERT INTO product(
                name, quantity, price_euro, date_modified, date_created
            ) VALUES ( ?,?,?,?,?);
        """, product.getName(), product.getQuantity(), product.getPrice(), currentTime, currentTime);
    }

    @Override
    public Product read(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject("""
                    SELECT * FROM product
                    WHERE id = ?
                """, productRowMapper, id);
    }

    @Override
    public List<Product> readList() {
        return jdbcTemplate.query("SELECT * FROM product", productRowMapper);
    }

    @Override
    public void update(Product product) throws DataAccessException {

    }

    @Override
    public void delete(int id) throws DataAccessException {
        jdbcTemplate.update("""
            UPDATE product
            SET deleted = true
            WHERE id = ?;
        """, id);
    }
}
