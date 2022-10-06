package com.skopei.demo.user;

import com.skopei.demo.abstraction.ICRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDAO")
@RequiredArgsConstructor
public class UserDAO implements ICRUD<UserProfile> {

    private final JdbcTemplate jdbcTemplate;
    //maps database column to POJO. column index is ignored but required arg for interface
    private final RowMapper<UserProfile> userRowMapper = (resultSet, i) -> UserProfile.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .email(resultSet.getString("email"))
            .creationDate(resultSet.getLong("date_created"))
            .modDate(resultSet.getLong("date_modified"))
            .deleted(resultSet.getBoolean("deleted"))
            .build();

    @Override
    public void create(UserProfile userProfile) throws DataAccessException {
        String sql = """
                    INSERT INTO user_profile( name, email, date_modified, date_created )
                    VALUES (?,?,?,?);
                """;
        long currentTime = System.currentTimeMillis(); // unix timestamp is compliant with UTC time and easy to work with

        jdbcTemplate.update(sql, userProfile.getName(), userProfile.getEmail(), currentTime, currentTime);
    }

    @Override
    public UserProfile read(int id) throws DataAccessException {
        String sql = """
                    SELECT * FROM user_profile
                    WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql , userRowMapper, id);
    }

    @Override
    public List<UserProfile> readList() {
        String sql = " SELECT * FROM user_profile";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public void update(UserProfile userProfile) throws DataAccessException {
        String sql = """
                    UPDATE user_profile
                    SET name=?, email=?, date_modified=?
                    WHERE id = ?;
                """;
        jdbcTemplate.update(sql, userProfile.getName(), userProfile.getEmail(), System.currentTimeMillis());
    }

    @Override
    public void delete(int id) throws DataAccessException {
        String sql = """
                    UPDATE user_profile
                    SET deleted = true,
                        date_modified=?
                    WHERE id = ?;
                """;
        jdbcTemplate.update(sql, System.currentTimeMillis(), id);
    }
}
