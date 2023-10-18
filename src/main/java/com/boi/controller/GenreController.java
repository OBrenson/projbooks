package com.boi.controller;

import com.boi.domain.Genre;
import com.boi.dto.EntitiesDto;
import com.boi.dto.GenreDto;
import com.boi.dto.Mappers;
import com.boi.repository.GenreRepository;
import com.boi.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping(value = "get/all")
    public List<GenreDto> getGenres(@RequestParam(required = false) Optional<String> sortBy,
                                    @RequestParam(required = false) Optional<Boolean> isDesc) {

        Sort sort = ControllerUtils.getSort(sortBy, isDesc);
        List<Genre> genres;
        if(sort == null) {
            genres = genreService.findAll();
        } else {
            genres = genreService.findAll(sort);
        }
        return Mappers.mapGenresToDtos(genres);
    }

    @PutMapping("put")
    public void putGenres(@RequestBody EntitiesDto<GenreDto> dtos) {
        List<Genre> genres = Mappers.mapDtosToGenres(dtos.getValue());
        genreService.saveAll(genres);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        genreService.deleteById(UUID.fromString(id));
    }
}
