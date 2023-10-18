package com.boi.controller;

import com.boi.domain.Book;
import com.boi.dto.BookDto;
import com.boi.dto.EntitiesDto;
import com.boi.dto.Mappers;
import com.boi.repository.BookRepository;
import com.boi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library/books")
public class BookController {

    @Autowired
    public BookService bookService;

    @GetMapping(value = "get/all/{page}/{size}")
    public ResponseEntity<List<BookDto>> getAllBooks(@PathVariable(value = "page") int page,
                                                    @PathVariable(value = "size") int size,
                                                    @RequestParam(required = false) Optional<String> sortBy,
                                                    @RequestParam(required = false) Optional<Boolean> isDesc,
                                                    @RequestParam(required = false) Optional<String> findBy) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Get-Header", "ExampleHeader");
        PageRequest pageRequest = ControllerUtils.preparePageRequest(page, size, sortBy, isDesc);
        if(findBy.isPresent()) {
            String[] find = findBy.get().split("=");
            return ResponseEntity.ok().headers(headers).body(
                    Mappers.mapBooksToDtos(bookService.findByColumn(find[0], find[1],pageRequest)));
        } else {
            return ResponseEntity.ok().headers(headers).body(
                    Mappers.mapBooksToDtos(bookService.findAll(pageRequest)));
        }
    }

    @PutMapping("put")
    public void putBooks(@RequestBody EntitiesDto<BookDto> bookDtos) {
        List<Book> books = Mappers.mapDtosToBooks(bookDtos.getValue());
        bookService.saveAll(books);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable(value = "id") String id) {
        bookService.deleteById(UUID.fromString(id));
    }

    @GetMapping("count")
    public long count() {
        return bookService.count();
    }
}
