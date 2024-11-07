package com.spacestar.starsystem.converter;

import com.spacestar.starsystem.converter.uses.DateConverter;
import com.spacestar.starsystem.model.dto.SatelliteDto;
import com.spacestar.starsystem.model.dto.create.SatelliteCreateDto;
import com.spacestar.starsystem.model.dto.update.SatelliteUpdateDto;
import com.spacestar.starsystem.model.entity.Satellite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring",
        implementationPackage = "com.spacestar.starsystem.converter.impl",
        uses = DateConverter.class
)
public interface SatelliteConverter {

    @Mapping(source = "dateCreation", target = "log.dateCreation")
    @Mapping(source = "lastModified", target = "log.lastModified")
    @Mapping(source = "version", target = "log.version")
    SatelliteDto convert(Satellite source);

    Satellite convert(SatelliteCreateDto source);

    Satellite convert(@MappingTarget Satellite satellite, SatelliteUpdateDto source);

    List<SatelliteDto> convert(List<Satellite> source);
}
