package com.spacestar.starsystem.controller;

import com.spacestar.starsystem.model.dto.page.PageContentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface PageController<D> {

    @GetMapping("/all")
    ResponseEntity<PageContentDto<D>> findAll(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                                              @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
                                              @RequestParam(name = "sortField", required = false, defaultValue = "") String sortField);
}
