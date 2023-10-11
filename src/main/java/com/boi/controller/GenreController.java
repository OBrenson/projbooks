package com.boi.controller;

import com.boi.domain.Genre;
import com.boi.dto.EntitiesDto;
import com.boi.dto.GenreDto;
import com.boi.dto.Mappers;
import com.boi.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library/genre")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping(value = "get/all")
    public List<GenreDto> getGenres() {
        List<Genre> genres = genreRepository.findAll();
        return Mappers.mapGenresToDtos(genres);
    }

    @PutMapping("put")
    public void putGenres(@RequestBody EntitiesDto<GenreDto> dtos) {
        List<Genre> genres = Mappers.mapDtosToGenres(dtos.getValue());
        genreRepository.saveAll(genres);
    }
}
