package com.boi.dto;

import com.boi.domain.Author;
import com.boi.domain.Book;
import com.boi.domain.Genre;
import com.boi.domain.PublishingHouse;

import java.util.List;
import java.util.stream.Collectors;

public class Mappers {

    public static List<BookDto> mapBooksToDtos(List<Book> books) {
        return books.stream().map(b -> new BookDto(
                b.getTitle(),
                b.getGenre().getId(),
                b.getPublishingHouse().getId(),
                b.getAuthors().stream().map(Author::getId).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }


    public static List<Book> mapDtosToBooks(List<BookDto> bookDtos) {
        return bookDtos.stream().map(d -> {
            Book book = new Book(d.getId());
            book.setGenre(new Genre(d.getId()));
            book.setPublishingHouse(new PublishingHouse(d.getPublishingHouseId()));
            book.setTitle(d.getTittle());
            if(d.authors != null && !d.authors.isEmpty()) {
                book.setAuthors(d.authors.stream().map(Author::new).collect(Collectors.toList()));
            }
            return book;
        }).collect(Collectors.toList());
    }
}
