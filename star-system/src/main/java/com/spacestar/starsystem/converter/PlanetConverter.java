package com.spacestar.starsystem.converter;

import com.spacestar.starsystem.converter.uses.DateConverter;
import com.spacestar.starsystem.model.dto.PlanetDto;
import com.spacestar.starsystem.model.dto.PlanetSatelliteDto;
import com.spacestar.starsystem.model.dto.create.PlanetCreateDto;
import com.spacestar.starsystem.model.dto.update.PlanetUpdateDto;
import com.spacestar.starsystem.model.entity.Planet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring",
        implementationPackage = "com.spacestar.starsystem.converter.impl",
        uses = {
                SatelliteConverter.class,
                DateConverter.class
        }
)
public interface PlanetConverter {

    @Mapping(source = "dateCreation", target = "log.dateCreation")
    @Mapping(source = "lastModified", target = "log.lastModified")
    @Mapping(source = "version", target = "log.version")
    PlanetDto convert(Planet source);

    @Mapping(source = "dateCreation", target = "log.dateCreation")
    @Mapping(source = "lastModified", target = "log.lastModified")
    @Mapping(source = "version", target = "log.version")
    @Mapping(source = "satelliteList", target = "satellites")
    PlanetSatelliteDto convertAndSatellites(Planet source);

    Planet convert(PlanetCreateDto source);

    Planet convert(@MappingTarget Planet planet, PlanetUpdateDto source);

    List<PlanetDto> convert(List<Planet> source);
}
