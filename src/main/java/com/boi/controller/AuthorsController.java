package com.boi.controller;

import com.boi.domain.Author;
import com.boi.dto.AuthorDto;
import com.boi.dto.EntitiesDto;
import com.boi.dto.Mappers;
import com.boi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library/authors")
public class AuthorsController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("get/all/{page}/{size}")
    public List<AuthorDto> getAuthors(@PathVariable int page,
                                      @PathVariable int size,
                                      @RequestParam(required = false) Optional<String> sortBy,
                                      @RequestParam(required = false) Optional<Boolean> isDesc,
                                      @RequestParam(required = false) Optional<String> name,
                                      @RequestParam(required = false) Optional<String> id) {

        List<Author> res = new ArrayList<>();
        if(page == 0 && size == 0) {
            res = authorRepository.findAll();
        } else {
            PageRequest pageRequest = ControllerUtils.preparePageRequest(page, size, sortBy, isDesc);
            if (name.isEmpty() && id.isEmpty()) {
                res = authorRepository.findAll(pageRequest).getContent();
            } else {
                if (id.isPresent()) {
                    res.add(authorRepository.findById(UUID.fromString(id.get())).orElse(null));
                } else {
                    res = authorRepository.findByNameLike("%" + name.get() + "%");
                }
            }
        }
        return Mappers.mapAuthorsToDtos(res);
    }

    @GetMapping("get/byBook/{id}")
    public List<AuthorDto> getAuthorsByBook(@PathVariable String id) {
        List<Author> res = authorRepository.findAllByBooks_Id(UUID.fromString(id));
        return Mappers.mapAuthorsToDtos(res);
    }

    @PutMapping("put")
    public void putAuthors(@RequestBody EntitiesDto<AuthorDto> dtos) {
        List<Author> authors = Mappers.mapDtosToAuthors(dtos.getValue());
        authorRepository.saveAll(authors);
    }
}
