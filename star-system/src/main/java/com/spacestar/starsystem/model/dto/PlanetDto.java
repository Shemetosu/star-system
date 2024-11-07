package com.spacestar.starsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanetDto {

    private Long id;
    private Long starId;
    private String name;
    private String type;
    private Boolean populated;
    private Long population;
    private LogDto log;
}
