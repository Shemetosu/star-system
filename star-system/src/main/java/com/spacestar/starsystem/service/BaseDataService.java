package com.spacestar.starsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BaseDataService<E> {

    boolean existsById(Long id);

    E findOne(Long id);

    Page<E> findAll(Pageable pageable);

    Page<E> findAll(Specification<E> specification, Pageable pageable);

    E save(E entity);

    void remove(Long id);
}
