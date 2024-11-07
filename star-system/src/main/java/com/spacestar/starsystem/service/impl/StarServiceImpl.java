package com.spacestar.starsystem.service.impl;

import com.spacestar.starsystem.model.entity.Star;
import com.spacestar.starsystem.repository.StarRepository;
import com.spacestar.starsystem.service.StarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
class StarServiceImpl implements StarService {

    private final StarRepository repository;

    @Override
    public boolean existsById(Long id) {
        return !repository.existsById(id);
    }

    @Override
    public Star findOne(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Star not found by id: " + id)
        );
    }

    @Override
    public Page<Star> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Star> findAll(Specification<Star> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public Star save(Star star) {
        return repository.save(star);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
