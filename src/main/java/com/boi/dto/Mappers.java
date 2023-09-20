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
                b.getId(),
                b.getTitle(),
                b.getGenre().getId(),
                b.getPublishingHouse().getId(),
                b.getAuthors().stream().map(Author::getId).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }


    public static List<Book> mapDtosToBooks(List<BookDto> bookDtos) {
        return bookDtos.stream().map(d -> {
            Book book = new Book(d.getId());
            book.setGenre(new Genre(d.getGenreId()));
            book.setPublishingHouse(new PublishingHouse(d.getPublishingHouseId()));
            book.setTitle(d.getTittle());
            if(d.getAuthors() != null && !d.getAuthors().isEmpty()) {
                book.setAuthors(d.getAuthors().stream().map(Author::new).collect(Collectors.toList()));
            }
            return book;
        }).collect(Collectors.toList());
    }

    public static List<Genre> mapDtosToGenres(List<GenreDto> genreDtos) {
        return genreDtos.stream()
                .map(d -> {
                    Genre genre = new Genre(d.getId());
                    genre.setName(d.getName());
                    return genre;
                })
                .collect(Collectors.toList());
    }

    public static List<GenreDto> mapGenresToDtos(List<Genre> genres) {
        return genres.stream().map(g -> new GenreDto(g.getId(), g.getName())).collect(Collectors.toList());
    }

    public static List<PublishingHouse> mapDtosToPubl(List<PublDto> publs) {
        return publs.stream()
                .map(p -> {
                    PublishingHouse publ = new PublishingHouse(p.getId());
                    publ.setName(p.getName());
                    return publ;
                })
                .collect(Collectors.toList());
    }

    public static List<PublDto> mapPublsToDtos(List<PublishingHouse> publis) {
        return publis.stream().map(p -> new PublDto(p.getId(), p.getName())).collect(Collectors.toList());
    }

    public static List<AuthorDto> mapAuthorsToDtos(List<Author> authors) {
        return authors.stream().map(a -> new AuthorDto(
                a.getId(),
                a.getName(),
                a.getBooks().stream().map(Book::getId).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    public static List<Author> mapDtosToAuthors(List<AuthorDto> authorDtos) {
        return authorDtos.stream().map(a -> {
            Author author = new Author(a.getId());
            author.setName(a.getName());
            if(a.getBooks() != null && !a.getBooks().isEmpty()) {
                author.setBooks(a.getBooks().stream().map(Book::new).collect(Collectors.toList()));
            }
            return author;
        }).collect(Collectors.toList());
    }
}
