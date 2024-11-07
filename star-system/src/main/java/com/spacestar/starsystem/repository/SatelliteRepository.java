package com.spacestar.starsystem.repository;

import com.spacestar.starsystem.model.entity.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, Long>, JpaSpecificationExecutor<Satellite> {

    List<Satellite> findAllByPlanetId(Long planetId);
}
