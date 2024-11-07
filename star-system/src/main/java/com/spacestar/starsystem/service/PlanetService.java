package com.spacestar.starsystem.service;

import com.spacestar.starsystem.model.entity.Planet;

import java.util.List;

public interface PlanetService extends BaseDataService<Planet> {

    List<Planet> findAllByStarId(Long starId);
}
