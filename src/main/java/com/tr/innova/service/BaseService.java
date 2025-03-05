package com.tr.innova.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<DTO, T, ID> {

    List<T> findAll();

    List<DTO> getAll();

    Optional<DTO> findById(ID id);

    T saveEntity(T entity);

    DTO save(DTO dto);

    void deleteById(ID id);
}

