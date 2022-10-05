package com.skopei.demo.abstraction;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CRUD<T> {

    void create(T t) throws DataAccessException;
    T read(int id) throws DataAccessException;
    List<T> readList(int... ids);
    void update(T t) throws DataAccessException;
    void delete(int id) throws DataAccessException;
}
