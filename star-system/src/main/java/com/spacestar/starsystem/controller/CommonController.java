package com.spacestar.starsystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CommonController<D, DC, DU> {

    @GetMapping("/one")
    ResponseEntity<D> findOne(@RequestParam Long id);

    @PostMapping("/create")
    ResponseEntity<D> create(@Valid @RequestBody DC dto);

    @PostMapping("/update")
    ResponseEntity<D> update(@Valid @RequestBody DU dto);

    @DeleteMapping("/remove")
    void remove(@RequestParam Long id);
}
