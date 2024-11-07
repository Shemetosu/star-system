package com.spacestar.starsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StarPlanetDto extends StarDto {

    private List<PlanetDto> planets;
}
