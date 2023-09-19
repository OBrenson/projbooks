package com.boi.controller;

import com.boi.domain.Book;
import com.boi.dto.BookDto;
import com.boi.dto.EntitiesDto;
import com.boi.dto.Mappers;
import com.boi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library/books")
public class BookController {

    @Autowired
    public BookRepository bookRepository;

    @GetMapping(value = "get/all/{from}/{to})")
    public List<BookDto> getAllBooks(@PathVariable(value = "from") int from,
                                     @PathVariable(value = "to") int to,
                                     @RequestParam Optional<String> sortBy,
                                     @RequestParam Optional<Boolean> isDesc) {

        PageRequest pageRequest = PageRequest.of(from, to);
        if(sortBy.isPresent()) {
            Sort sort=Sort.by(sortBy.get());
            if(isDesc.isPresent()) {
                if(isDesc.get()) {
                    sort = sort.descending();
                } else {
                    sort = sort.ascending();
                }
            }
            pageRequest = pageRequest.withSort(sort);
        }
        return Mappers.mapBooksToDtos(bookRepository.findAll(pageRequest).getContent());
    }

    @PutMapping("put")
    public void putBooks(@RequestBody EntitiesDto<BookDto> bookDtos) {
        List<Book> books = Mappers.mapDtosToBooks(bookDtos.getValue());
        bookRepository.saveAll(books);
    }

}
