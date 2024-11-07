package com.spacestar.starsystem.repository;

import com.spacestar.starsystem.model.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long>, JpaSpecificationExecutor<Planet> {

    List<Planet> findAllByStarId(Long starId);
}
