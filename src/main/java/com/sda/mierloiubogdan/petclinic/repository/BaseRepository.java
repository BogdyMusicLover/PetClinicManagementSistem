package com.sda.mierloiubogdan.petclinic.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    void create(T entity);
    List<T> getAll();

    Optional<T> findById(int id);

    void updateById(int id, T entity);

    void deleteById(int id);

    void deleteAll();
}
