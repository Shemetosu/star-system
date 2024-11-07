package com.spacestar.starsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanetSatelliteDto extends PlanetDto {

    private List<SatelliteDto> satellites;
}
