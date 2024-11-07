package com.spacestar.starsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StarPlanetSatelliteDto extends StarDto {

    private List<PlanetSatelliteDto> planets;
}
