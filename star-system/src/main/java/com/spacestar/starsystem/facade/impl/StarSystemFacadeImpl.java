package com.spacestar.starsystem.facade.impl;

import com.spacestar.starsystem.converter.PlanetConverter;
import com.spacestar.starsystem.converter.SatelliteConverter;
import com.spacestar.starsystem.converter.StarConverter;
import com.spacestar.starsystem.facade.StarSystemFacade;
import com.spacestar.starsystem.model.dto.PlanetDto;
import com.spacestar.starsystem.model.dto.PlanetSatelliteDto;
import com.spacestar.starsystem.model.dto.SatelliteDto;
import com.spacestar.starsystem.model.dto.StarPlanetDto;
import com.spacestar.starsystem.model.dto.StarPlanetSatelliteDto;
import com.spacestar.starsystem.model.dto.create.PlanetCreateDto;
import com.spacestar.starsystem.model.dto.create.SatelliteCreateDto;
import com.spacestar.starsystem.model.dto.create.StarCreateDto;
import com.spacestar.starsystem.model.dto.StarDto;
import com.spacestar.starsystem.model.dto.page.PageContentDto;
import com.spacestar.starsystem.model.dto.page.PageDto;
import com.spacestar.starsystem.model.dto.page.PageParamDto;
import com.spacestar.starsystem.model.dto.update.PlanetUpdateDto;
import com.spacestar.starsystem.model.dto.update.SatelliteUpdateDto;
import com.spacestar.starsystem.model.dto.update.StarUpdateDto;
import com.spacestar.starsystem.model.entity.Planet;
import com.spacestar.starsystem.model.entity.Satellite;
import com.spacestar.starsystem.model.entity.Star;
import com.spacestar.starsystem.repository.specification.PlanetSpecification;
import com.spacestar.starsystem.repository.specification.SatelliteSpecification;
import com.spacestar.starsystem.service.PlanetService;
import com.spacestar.starsystem.service.SatelliteService;
import com.spacestar.starsystem.service.StarService;
import com.spacestar.starsystem.util.PageUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
class StarSystemFacadeImpl implements StarSystemFacade {

    private final StarService starService;
    private final PlanetService planetService;
    private final SatelliteService satelliteService;
    private final StarConverter starConverter;
    private final PlanetConverter planetConverter;
    private final SatelliteConverter satelliteConverter;

    @Override
    public StarDto findOneStar(Long id) {
        return starConverter.convert(starService.findOne(id));
    }

    @Override
    public PageContentDto<StarDto> findAllStars(PageParamDto param) {
        var pageable = PageUtils.page(param);
        Page<Star> page = starService.findAll(pageable);
        return new PageContentDto<>(
                new PageDto(param.pageNumber(), param.pageSize(), page.getTotalPages(), page.getTotalElements()),
                page.getContent().isEmpty()
                        ? List.of()
                        : starConverter.convert(page.getContent())
        );
    }

    @Override
    public StarDto createStar(StarCreateDto dto) {
        return starConverter.convert(
                starService.save(
                        starConverter.convert(dto)
                )
        );
    }

    @Override
    public StarDto updateStar(StarUpdateDto dto) {
        Star star = starConverter.convert(starService.findOne(dto.getId()), dto);
        return starConverter.convert(
                starService.save(star)
        );
    }

    @Override
    public void removeStar(Long id) {
        starService.remove(id);
    }

    //---

    @Override
    public PlanetDto findOnePlanet(Long id) {
        return planetConverter.convert(planetService.findOne(id));
    }

    @Override
    public PageContentDto<PlanetDto> findAllPlanets(PageParamDto param) {
        var pageable = PageUtils.page(param);
        return planetPage(param, planetService.findAll(pageable));
    }

    @Override
    public PageContentDto<PlanetDto> findAllPlanets(PageParamDto param, Long starId) {
        var pageable = PageUtils.page(param);
        if (starId == null) {
            return findAllPlanets(param);
        } else {
            var specification = Specification.where(PlanetSpecification.findAllByStarId(starId));
            return planetPage(param, planetService.findAll(specification, pageable));
        }
    }

    @Override
    public PlanetDto createPlanet(PlanetCreateDto dto) {
        if (starService.existsById(dto.getStarId())) {
            throw new EntityNotFoundException("Star not found by id: " + dto.getStarId());
        }
        Planet planet = planetConverter.convert(dto);
        planet.setStar(starService.findOne(dto.getStarId()));
        return planetConverter.convert(planetService.save(planet));
    }

    @Override
    public PlanetDto updatePlanet(PlanetUpdateDto dto) {
        if (starService.existsById(dto.getStarId())) {
            throw new EntityNotFoundException("Star not found by id: " + dto.getStarId());
        }
        Planet planet = planetService.findOne(dto.getId());
        planet = planetConverter.convert(planet, dto);
        planet.setStar(starService.findOne(dto.getStarId()));
        return planetConverter.convert(planetService.save(planet));
    }

    @Override
    public void removePlanet(Long id) {
        planetService.remove(id);
    }

    //---

    @Override
    public SatelliteDto findOneSatellite(Long id) {
        return satelliteConverter.convert(satelliteService.findOne(id));
    }

    @Override
    public PageContentDto<SatelliteDto> findAllSatellites(PageParamDto param) {
        var pageable = PageUtils.page(param);
        return satellitePage(param, satelliteService.findAll(pageable));
    }

    @Override
    public PageContentDto<SatelliteDto> findAllSatellites(PageParamDto param, Long planetId, Long starId) {
        Specification<Satellite> specification;
        if (planetId == null && starId == null) {
            return findAllSatellites(param);
        } else if (planetId != null) {
            specification = Specification.where(SatelliteSpecification.findAllByPlanetId(planetId));
        } else {
            specification = Specification.where(SatelliteSpecification.findAllByStarId(starId));
        }
        var pageable = PageUtils.page(param);
        return satellitePage(param, satelliteService.findAll(specification, pageable));
    }

    @Override
    public SatelliteDto createSatellite(SatelliteCreateDto dto) {
        if (planetService.existsById(dto.getPlanetId())) {
            throw new EntityNotFoundException("Planet not found by id: " + dto.getPlanetId());
        }
        Satellite satellite = satelliteConverter.convert(dto);
        satellite.setPlanet(planetService.findOne(dto.getPlanetId()));
        return satelliteConverter.convert(satelliteService.save(satellite));
    }

    @Override
    public SatelliteDto updateSatellite(SatelliteUpdateDto dto) {
        if (planetService.existsById(dto.getPlanetId())) {
            throw new EntityNotFoundException("Planet not found by id: " + dto.getPlanetId());
        }
        Satellite satellite = satelliteService.findOne(dto.getId());
        satellite = satelliteConverter.convert(satellite, dto);
        satellite.setPlanet(planetService.findOne(dto.getPlanetId()));
        return satelliteConverter.convert(satelliteService.save(satellite));
    }

    @Override
    public void removeSatellite(Long id) {
        satelliteService.remove(id);
    }

    private PageContentDto<PlanetDto> planetPage(PageParamDto param, Page<Planet> page) {
        return new PageContentDto<>(
                new PageDto(param.pageNumber(), param.pageSize(), page.getTotalPages(), page.getTotalElements()),
                page.getContent().isEmpty()
                        ? List.of()
                        : planetConverter.convert(page.getContent())
        );
    }

    private PageContentDto<SatelliteDto> satellitePage(PageParamDto param, Page<Satellite> page) {
        return new PageContentDto<>(
                new PageDto(param.pageNumber(), param.pageSize(), page.getTotalPages(), page.getTotalElements()),
                page.getContent().isEmpty()
                        ? List.of()
                        : satelliteConverter.convert(page.getContent())
        );
    }
}
