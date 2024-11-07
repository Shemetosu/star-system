package com.spacestar.starsystem.service.impl;

import com.spacestar.starsystem.model.entity.Satellite;
import com.spacestar.starsystem.repository.SatelliteRepository;
import com.spacestar.starsystem.service.SatelliteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SatelliteServiceImpl implements SatelliteService {

    private final SatelliteRepository repository;

    @Override
    public boolean existsById(Long id) {
        throw new RuntimeException("NOOOOO");
    }

    @Override
    public Satellite findOne(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Satellite not found by id: " + id)
        );
    }

    @Override
    public Page<Satellite> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Satellite> findAll(Specification<Satellite> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public Satellite save(Satellite entity) {
        return repository.save(entity);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
