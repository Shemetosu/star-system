package com.spacestar.starsystem.converter;

import com.spacestar.starsystem.converter.uses.DateConverter;
import com.spacestar.starsystem.model.dto.StarPlanetDto;
import com.spacestar.starsystem.model.dto.StarPlanetSatelliteDto;
import com.spacestar.starsystem.model.dto.create.StarCreateDto;
import com.spacestar.starsystem.model.dto.StarDto;
import com.spacestar.starsystem.model.dto.update.StarUpdateDto;
import com.spacestar.starsystem.model.entity.Star;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring",
        implementationPackage = "com.spacestar.starsystem.converter.impl",
        uses = {
                PlanetConverter.class,
                DateConverter.class
        }
)
public interface StarConverter {

    @Mapping(source = "dateCreation", target = "log.dateCreation")
    @Mapping(source = "lastModified", target = "log.lastModified")
    @Mapping(source = "version", target = "log.version")
    StarDto convert(Star source);

    @Mapping(source = "dateCreation", target = "log.dateCreation")
    @Mapping(source = "lastModified", target = "log.lastModified")
    @Mapping(source = "version", target = "log.version")
    @Mapping(source = "planetList", target = "planets")
    StarPlanetDto convertAndPlanets(Star source);

    @Mapping(source = "dateCreation", target = "log.dateCreation")
    @Mapping(source = "lastModified", target = "log.lastModified")
    @Mapping(source = "version", target = "log.version")
    @Mapping(source = "planetList", target = "planets")
    StarPlanetSatelliteDto convertAndPlanetsAndSatellites(Star source);

    Star convert(StarCreateDto source);

    Star convert(@MappingTarget Star star, StarUpdateDto source);

    List<StarDto> convert(List<Star> source);
}
