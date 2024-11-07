package com.spacestar.starsystem.controller;

import com.spacestar.starsystem.facade.StarSystemFacade;
import com.spacestar.starsystem.model.dto.StarDto;
import com.spacestar.starsystem.model.dto.create.StarCreateDto;
import com.spacestar.starsystem.model.dto.page.PageContentDto;
import com.spacestar.starsystem.model.dto.page.PageParamDto;
import com.spacestar.starsystem.model.dto.update.StarUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stars")
public class StarController implements PageController<StarDto>, CommonController<StarDto, StarCreateDto, StarUpdateDto> {

    private final StarSystemFacade facade;

    @Override
    public ResponseEntity<StarDto> findOne(Long id) {
        return new ResponseEntity<>(facade.findOneStar(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageContentDto<StarDto>> findAll(int pageNumber,
                                                           int pageSize,
                                                           String sortOrder,
                                                           String sortField) {
        var pageParam = new PageParamDto(pageNumber, pageSize, sortOrder, sortField);
        return new ResponseEntity<>(facade.findAllStars(pageParam), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StarDto> create(StarCreateDto dto) {
        return new ResponseEntity<>(facade.createStar(dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StarDto> update(StarUpdateDto dto) {
        return new ResponseEntity<>(facade.updateStar(dto), HttpStatus.OK);
    }

    @Override
    public void remove(Long id) {
        facade.removeStar(id);
    }
}
