package com.boi;

import com.boi.domain.Author;
import com.boi.domain.Book;
import com.boi.dto.BookDto;
import com.boi.dto.GenreDto;
import com.boi.dto.Mappers;
import com.boi.dto.PublDto;
import com.boi.repository.AuthorRepository;
import com.boi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TestBookRepository {

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public AuthorRepository authorRepository;

    @Test
    public void testSave() {
        List<BookDto> dtos = new ArrayList<>();
        BookDto dto = new BookDto();
        dto.setTitle("title");
        dto.setGenre(new GenreDto(UUID.fromString("f95739ab-a751-4af9-8016-f25a17257bed"), "asd"));
        dto.setPubl(new PublDto(UUID.fromString("f95739ab-a751-4af9-8016-f25a17257bed"), "pub"));
        dtos.add(dto);
        List<Book> books = Mappers.mapDtosToBooks(dtos);
        bookRepository.saveAll(books);
    }

    @Test
    public void testCustom() {
        PageRequest pageRequest = PageRequest.of(1, 5);
        Sort sort = Sort.by(Sort.Direction.ASC,"title");
        pageRequest = pageRequest.withSort(sort);
        var res = bookRepository.findByColumn("genre",
                UUID.fromString("f95739ab-a751-4af9-8016-f25a17257bed"), pageRequest);
    }

    @Test
    public void testGetBooksByAuthors() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Sort sort = Sort.by(Sort.Direction.DESC,"name");
        List<UUID> ids = new ArrayList<>();
        ids.add(UUID.fromString("f1ab6f91-4e42-4ca4-b87a-ac2590b6f52f"));
        ids.add(UUID.fromString("7570552f-b85e-434d-8843-98008cdacc4b"));
        var res = bookRepository.findByColumn("authors_id", ids.add(UUID.fromString("f1ab6f91-4e42-4ca4-b87a-ac2590b6f52f")), pageRequest);
    }
}
