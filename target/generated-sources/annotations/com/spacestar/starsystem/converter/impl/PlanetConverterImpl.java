package com.spacestar.starsystem.converter.impl;

import com.spacestar.starsystem.converter.PlanetConverter;
import com.spacestar.starsystem.converter.SatelliteConverter;
import com.spacestar.starsystem.converter.uses.DateConverter;
import com.spacestar.starsystem.model.dto.LogDto;
import com.spacestar.starsystem.model.dto.PlanetDto;
import com.spacestar.starsystem.model.dto.PlanetSatelliteDto;
import com.spacestar.starsystem.model.dto.create.PlanetCreateDto;
import com.spacestar.starsystem.model.dto.update.PlanetUpdateDto;
import com.spacestar.starsystem.model.entity.Planet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-07T13:44:50+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PlanetConverterImpl implements PlanetConverter {

    @Autowired
    private SatelliteConverter satelliteConverter;

    @Override
    public PlanetDto convert(Planet source) {
        if ( source == null ) {
            return null;
        }

        PlanetDto planetDto = new PlanetDto();

        planetDto.setLog( planetToLogDto( source ) );
        planetDto.setId( source.getId() );
        planetDto.setStarId( source.getStarId() );
        planetDto.setName( source.getName() );
        planetDto.setType( source.getType() );
        planetDto.setPopulated( source.getPopulated() );
        planetDto.setPopulation( source.getPopulation() );

        return planetDto;
    }

    @Override
    public PlanetSatelliteDto convertAndSatellites(Planet source) {
        if ( source == null ) {
            return null;
        }

        PlanetSatelliteDto planetSatelliteDto = new PlanetSatelliteDto();

        planetSatelliteDto.setLog( planetToLogDto1( source ) );
        planetSatelliteDto.setSatellites( satelliteConverter.convert( source.getSatelliteList() ) );
        planetSatelliteDto.setId( source.getId() );
        planetSatelliteDto.setStarId( source.getStarId() );
        planetSatelliteDto.setName( source.getName() );
        planetSatelliteDto.setType( source.getType() );
        planetSatelliteDto.setPopulated( source.getPopulated() );
        planetSatelliteDto.setPopulation( source.getPopulation() );

        return planetSatelliteDto;
    }

    @Override
    public Planet convert(PlanetCreateDto source) {
        if ( source == null ) {
            return null;
        }

        Planet planet = new Planet();

        planet.setName( source.getName() );
        planet.setType( source.getType() );
        planet.setStarId( source.getStarId() );
        planet.setPopulated( source.getPopulated() );
        planet.setPopulation( source.getPopulation() );

        return planet;
    }

    @Override
    public Planet convert(Planet planet, PlanetUpdateDto source) {
        if ( source == null ) {
            return planet;
        }

        planet.setId( source.getId() );
        planet.setName( source.getName() );
        planet.setType( source.getType() );
        planet.setStarId( source.getStarId() );
        planet.setPopulated( source.getPopulated() );
        planet.setPopulation( source.getPopulation() );

        return planet;
    }

    @Override
    public List<PlanetDto> convert(List<Planet> source) {
        if ( source == null ) {
            return null;
        }

        List<PlanetDto> list = new ArrayList<PlanetDto>( source.size() );
        for ( Planet planet : source ) {
            list.add( convert( planet ) );
        }

        return list;
    }

    protected LogDto planetToLogDto(Planet planet) {
        if ( planet == null ) {
            return null;
        }

        LogDto logDto = new LogDto();

        logDto.setDateCreation( DateConverter.convertDate( planet.getDateCreation() ) );
        logDto.setLastModified( DateConverter.convertDate( planet.getLastModified() ) );
        logDto.setVersion( planet.getVersion() );

        return logDto;
    }

    protected LogDto planetToLogDto1(Planet planet) {
        if ( planet == null ) {
            return null;
        }

        LogDto logDto = new LogDto();

        logDto.setDateCreation( DateConverter.convertDate( planet.getDateCreation() ) );
        logDto.setLastModified( DateConverter.convertDate( planet.getLastModified() ) );
        logDto.setVersion( planet.getVersion() );

        return logDto;
    }
}
