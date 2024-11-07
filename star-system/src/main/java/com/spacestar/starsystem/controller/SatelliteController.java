package com.spacestar.starsystem.controller;

import com.spacestar.starsystem.facade.StarSystemFacade;
import com.spacestar.starsystem.model.dto.SatelliteDto;
import com.spacestar.starsystem.model.dto.create.SatelliteCreateDto;
import com.spacestar.starsystem.model.dto.page.PageContentDto;
import com.spacestar.starsystem.model.dto.page.PageParamDto;
import com.spacestar.starsystem.model.dto.update.SatelliteUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/satellites")
public class SatelliteController implements PageController<SatelliteDto>, CommonController<SatelliteDto, SatelliteCreateDto, SatelliteUpdateDto> {

    private final StarSystemFacade starSystemFacade;

    @Override
    public ResponseEntity<SatelliteDto> findOne(Long id) {
        return new ResponseEntity<>(starSystemFacade.findOneSatellite(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageContentDto<SatelliteDto>> findAll(int pageNumber, int pageSize, String sortOrder, String sortField) {
        return null;
    }

    @GetMapping("/allBy")
    public ResponseEntity<PageContentDto<SatelliteDto>> findAll(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                                @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
                                                                @RequestParam(name = "sortField", required = false, defaultValue = "") String sortField,
                                                                @RequestParam(name = "planetId", required = false) Long planetId,
                                                                @RequestParam(name = "starId", required = false) Long starId) {
        var pageParam = new PageParamDto(pageNumber, pageSize, sortOrder, sortField);
        return new ResponseEntity<>(starSystemFacade.findAllSatellites(pageParam, planetId, starId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SatelliteDto> create(SatelliteCreateDto dto) {
        return new ResponseEntity<>(starSystemFacade.createSatellite(dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SatelliteDto> update(SatelliteUpdateDto dto) {
        return new ResponseEntity<>(starSystemFacade.updateSatellite(dto), HttpStatus.OK);
    }

    @Override
    public void remove(Long id) {
        starSystemFacade.removeSatellite(id);
    }
}
