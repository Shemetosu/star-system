package com.spacestar.starsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SatelliteDto {

    private Long id;
    private String name;
    private LogDto log;
}
