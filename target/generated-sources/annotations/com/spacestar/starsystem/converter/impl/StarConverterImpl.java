package com.spacestar.starsystem.converter.impl;

import com.spacestar.starsystem.converter.PlanetConverter;
import com.spacestar.starsystem.converter.StarConverter;
import com.spacestar.starsystem.converter.uses.DateConverter;
import com.spacestar.starsystem.model.dto.LogDto;
import com.spacestar.starsystem.model.dto.PlanetSatelliteDto;
import com.spacestar.starsystem.model.dto.StarDto;
import com.spacestar.starsystem.model.dto.StarPlanetDto;
import com.spacestar.starsystem.model.dto.StarPlanetSatelliteDto;
import com.spacestar.starsystem.model.dto.create.StarCreateDto;
import com.spacestar.starsystem.model.dto.update.StarUpdateDto;
import com.spacestar.starsystem.model.entity.Planet;
import com.spacestar.starsystem.model.entity.Star;
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
public class StarConverterImpl implements StarConverter {

    @Autowired
    private PlanetConverter planetConverter;

    @Override
    public StarDto convert(Star source) {
        if ( source == null ) {
            return null;
        }

        StarDto starDto = new StarDto();

        starDto.setLog( starToLogDto( source ) );
        starDto.setId( source.getId() );
        starDto.setName( source.getName() );
        starDto.setType( source.getType() );

        return starDto;
    }

    @Override
    public StarPlanetDto convertAndPlanets(Star source) {
        if ( source == null ) {
            return null;
        }

        StarPlanetDto starPlanetDto = new StarPlanetDto();

        starPlanetDto.setLog( starToLogDto1( source ) );
        starPlanetDto.setPlanets( planetConverter.convert( source.getPlanetList() ) );
        starPlanetDto.setId( source.getId() );
        starPlanetDto.setName( source.getName() );
        starPlanetDto.setType( source.getType() );

        return starPlanetDto;
    }

    @Override
    public StarPlanetSatelliteDto convertAndPlanetsAndSatellites(Star source) {
        if ( source == null ) {
            return null;
        }

        StarPlanetSatelliteDto starPlanetSatelliteDto = new StarPlanetSatelliteDto();

        starPlanetSatelliteDto.setLog( starToLogDto2( source ) );
        starPlanetSatelliteDto.setPlanets( planetListToPlanetSatelliteDtoList( source.getPlanetList() ) );
        starPlanetSatelliteDto.setId( source.getId() );
        starPlanetSatelliteDto.setName( source.getName() );
        starPlanetSatelliteDto.setType( source.getType() );

        return starPlanetSatelliteDto;
    }

    @Override
    public Star convert(StarCreateDto source) {
        if ( source == null ) {
            return null;
        }

        Star star = new Star();

        star.setName( source.getName() );
        star.setType( source.getType() );

        return star;
    }

    @Override
    public Star convert(Star star, StarUpdateDto source) {
        if ( source == null ) {
            return star;
        }

        star.setId( source.getId() );
        star.setName( source.getName() );
        star.setType( source.getType() );

        return star;
    }

    @Override
    public List<StarDto> convert(List<Star> source) {
        if ( source == null ) {
            return null;
        }

        List<StarDto> list = new ArrayList<StarDto>( source.size() );
        for ( Star star : source ) {
            list.add( convert( star ) );
        }

        return list;
    }

    protected LogDto starToLogDto(Star star) {
        if ( star == null ) {
            return null;
        }

        LogDto logDto = new LogDto();

        logDto.setDateCreation( DateConverter.convertDate( star.getDateCreation() ) );
        logDto.setLastModified( DateConverter.convertDate( star.getLastModified() ) );
        logDto.setVersion( star.getVersion() );

        return logDto;
    }

    protected LogDto starToLogDto1(Star star) {
        if ( star == null ) {
            return null;
        }

        LogDto logDto = new LogDto();

        logDto.setDateCreation( DateConverter.convertDate( star.getDateCreation() ) );
        logDto.setLastModified( DateConverter.convertDate( star.getLastModified() ) );
        logDto.setVersion( star.getVersion() );

        return logDto;
    }

    protected LogDto starToLogDto2(Star star) {
        if ( star == null ) {
            return null;
        }

        LogDto logDto = new LogDto();

        logDto.setDateCreation( DateConverter.convertDate( star.getDateCreation() ) );
        logDto.setLastModified( DateConverter.convertDate( star.getLastModified() ) );
        logDto.setVersion( star.getVersion() );

        return logDto;
    }

    protected List<PlanetSatelliteDto> planetListToPlanetSatelliteDtoList(List<Planet> list) {
        if ( list == null ) {
            return null;
        }

        List<PlanetSatelliteDto> list1 = new ArrayList<PlanetSatelliteDto>( list.size() );
        for ( Planet planet : list ) {
            list1.add( planetConverter.convertAndSatellites( planet ) );
        }

        return list1;
    }
}
