package dev.spring.datajdbc.dao;


import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    List<T> fetchAll();
    Optional<T> fetchOne(Long id);
    int updateById(Long id, T requestBody);
    int deleteById(Long id);
    int create(T requestBody);
}
