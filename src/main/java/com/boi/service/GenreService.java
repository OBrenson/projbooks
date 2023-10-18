package com.boi.service;

import com.boi.domain.Genre;
import com.boi.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GenreService implements DataSourceService {

    private GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public List<Genre> findAll(Sort sort) {
        return genreRepository.findAll(sort);
    }

    public void saveAll(List<Genre> genres) {
        genreRepository.saveAll(genres);
    }

    public void deleteById(UUID id) {
        genreRepository.deleteById(id);
    }
}
