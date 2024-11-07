package com.spacestar.starsystem.service.impl;

import com.spacestar.starsystem.model.entity.Planet;
import com.spacestar.starsystem.repository.PlanetRepository;
import com.spacestar.starsystem.service.PlanetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository repository;

    @Override
    public boolean existsById(Long id) {
        return !repository.existsById(id);
    }

    @Override
    public Planet findOne(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Planet not found by id: " + id)
        );
    }

    @Override
    public Page<Planet> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Planet> findAll(Specification<Planet> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public List<Planet> findAllByStarId(Long starId) {
        return repository.findAllByStarId(starId);
    }

    @Override
    public Planet save(Planet entity) {
        return repository.save(entity);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
