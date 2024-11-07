package com.spacestar.starsystem.controller;

import com.spacestar.starsystem.facade.StarSystemFacade;
import com.spacestar.starsystem.model.dto.PlanetDto;
import com.spacestar.starsystem.model.dto.SatelliteDto;
import com.spacestar.starsystem.model.dto.create.PlanetCreateDto;
import com.spacestar.starsystem.model.dto.page.PageContentDto;
import com.spacestar.starsystem.model.dto.page.PageParamDto;
import com.spacestar.starsystem.model.dto.update.PlanetUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/planets")
public class PlanetController implements PageController<SatelliteDto>, CommonController<PlanetDto, PlanetCreateDto, PlanetUpdateDto> {

    private final StarSystemFacade starSystemFacade;

    @Override
    public ResponseEntity<PlanetDto> findOne(Long id) {
        return new ResponseEntity<>(starSystemFacade.findOnePlanet(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageContentDto<SatelliteDto>> findAll(int pageNumber, int pageSize, String sortOrder, String sortField) {
        var pageParam = new PageParamDto(pageNumber, pageSize, sortOrder, sortField);
        return new ResponseEntity<>(starSystemFacade.findAllSatellites(pageParam), HttpStatus.OK);
    }

    @GetMapping("/allBy")
    public ResponseEntity<PageContentDto<PlanetDto>> findAll(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                                @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
                                                                @RequestParam(name = "sortField", required = false, defaultValue = "") String sortField,
                                                                @RequestParam(name = "starId", required = false) Long starId) {
        var pageParam = new PageParamDto(pageNumber, pageSize, sortOrder, sortField);
        return new ResponseEntity<>(starSystemFacade.findAllPlanets(pageParam, starId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlanetDto> create(PlanetCreateDto dto) {
        return new ResponseEntity<>(starSystemFacade.createPlanet(dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlanetDto> update(PlanetUpdateDto dto) {
        return new ResponseEntity<>(starSystemFacade.updatePlanet(dto), HttpStatus.OK);
    }

    @Override
    public void remove(Long id) {
        starSystemFacade.removePlanet(id);
    }
}
