package com.spacestar.starsystem.converter.impl;

import com.spacestar.starsystem.converter.SatelliteConverter;
import com.spacestar.starsystem.converter.uses.DateConverter;
import com.spacestar.starsystem.model.dto.LogDto;
import com.spacestar.starsystem.model.dto.SatelliteDto;
import com.spacestar.starsystem.model.dto.create.SatelliteCreateDto;
import com.spacestar.starsystem.model.dto.update.SatelliteUpdateDto;
import com.spacestar.starsystem.model.entity.Satellite;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-07T13:44:50+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class SatelliteConverterImpl implements SatelliteConverter {

    @Override
    public SatelliteDto convert(Satellite source) {
        if ( source == null ) {
            return null;
        }

        SatelliteDto satelliteDto = new SatelliteDto();

        satelliteDto.setLog( satelliteToLogDto( source ) );
        satelliteDto.setId( source.getId() );
        satelliteDto.setName( source.getName() );

        return satelliteDto;
    }

    @Override
    public Satellite convert(SatelliteCreateDto source) {
        if ( source == null ) {
            return null;
        }

        Satellite satellite = new Satellite();

        satellite.setName( source.getName() );
        satellite.setPlanetId( source.getPlanetId() );

        return satellite;
    }

    @Override
    public Satellite convert(Satellite satellite, SatelliteUpdateDto source) {
        if ( source == null ) {
            return satellite;
        }

        satellite.setId( source.getId() );
        satellite.setName( source.getName() );
        satellite.setPlanetId( source.getPlanetId() );

        return satellite;
    }

    @Override
    public List<SatelliteDto> convert(List<Satellite> source) {
        if ( source == null ) {
            return null;
        }

        List<SatelliteDto> list = new ArrayList<SatelliteDto>( source.size() );
        for ( Satellite satellite : source ) {
            list.add( convert( satellite ) );
        }

        return list;
    }

    protected LogDto satelliteToLogDto(Satellite satellite) {
        if ( satellite == null ) {
            return null;
        }

        LogDto logDto = new LogDto();

        logDto.setDateCreation( DateConverter.convertDate( satellite.getDateCreation() ) );
        logDto.setLastModified( DateConverter.convertDate( satellite.getLastModified() ) );
        logDto.setVersion( satellite.getVersion() );

        return logDto;
    }
}
