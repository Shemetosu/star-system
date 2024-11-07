package com.spacestar.starsystem.facade;

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
import com.spacestar.starsystem.model.dto.page.PageParamDto;
import com.spacestar.starsystem.model.dto.update.PlanetUpdateDto;
import com.spacestar.starsystem.model.dto.update.SatelliteUpdateDto;
import com.spacestar.starsystem.model.dto.update.StarUpdateDto;

import java.util.List;

public interface StarSystemFacade {

    StarDto findOneStar(Long id);

    PageContentDto<StarDto> findAllStars(PageParamDto pageParam);

    StarDto createStar(StarCreateDto dto);

    StarDto updateStar(StarUpdateDto dto);

    void removeStar(Long id);

    //---

    PlanetDto findOnePlanet(Long id);

    PageContentDto<PlanetDto> findAllPlanets(PageParamDto param);

    PageContentDto<PlanetDto> findAllPlanets(PageParamDto param, Long starId);

    PlanetDto createPlanet(PlanetCreateDto dto);

    PlanetDto updatePlanet(PlanetUpdateDto dto);

    void removePlanet(Long id);

    //---

    SatelliteDto findOneSatellite(Long id);

    PageContentDto<SatelliteDto> findAllSatellites(PageParamDto param);

    PageContentDto<SatelliteDto> findAllSatellites(PageParamDto param, Long planetId, Long starId);

    SatelliteDto createSatellite(SatelliteCreateDto dto);

    SatelliteDto updateSatellite(SatelliteUpdateDto dto);

    void removeSatellite(Long id);
}
