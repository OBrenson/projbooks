package com.boi.dto;

import com.boi.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class Mappers {

    public static List<BookDto> mapBooksToDtos(List<Book> books) {
        return books.stream().map(b -> new BookDto(
                b.getId(),
                b.getTitle(),
                new GenreDto(b.getGenre().getId(), b.getGenre().getName()),
                new PublDto(b.getPublishingHouse().getId(), b.getPublishingHouse().getName()),
                b.getAuthors().stream().map(a -> new AuthorDto(a.getId(), a.getName())).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }


    public static List<Book> mapDtosToBooks(List<BookDto> bookDtos) {
        return bookDtos.stream().map(d -> {
            Book book = new Book(d.getId());
            book.setGenre(new Genre(d.getGenre().getId()));
            book.setPublishingHouse(new PublishingHouse(d.getPubl().getId()));
            book.setTitle(d.getTitle());
            if(d.getAuthors() != null && !d.getAuthors().isEmpty()) {
                book.setAuthors(d.getAuthors().stream().map(a -> new Author(a.getId())).collect(Collectors.toList()));
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

    public static List<AppUser> mapDtosToUsers(List<UserDto> userDtos, PasswordEncoder encoder) {
        return userDtos.stream()
                .map(u -> {
                    AppUser user = new AppUser(u.getId());
                    user.setName(u.getName());
                    user.setRoles(u.getRoles().stream()
                            .map(r -> new Role(r.getId(), r.getName())).collect(Collectors.toSet()));
                    user.setPassword(encoder.encode(u.getPassword()));
                    return user;
                })
                .collect(Collectors.toList());
    }

    public static List<GenreDto> mapGenresToDtos(List<Genre> genres) {
        return genres.stream().map(g -> new GenreDto(g.getId(), g.getName())).collect(Collectors.toList());
    }

    public static List<UserDto> mapUsersToDtos(List<AppUser> users) {
        return users.stream().map(u ->
                new UserDto(
                        u.getId(),
                        u.getName(),
                        u.getRoles().stream().map(r -> new RoleDto(r.getId(), r.getName())).collect(Collectors.toSet())
                )
        ).collect(Collectors.toList());
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
                a.getBooks().stream().map(b -> new BookDto(b.getId(), b.getTitle())).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    public static List<Author> mapDtosToAuthors(List<AuthorDto> authorDtos) {
        return authorDtos.stream().map(a -> {
            Author author = new Author(a.getId());
            author.setName(a.getName());
            if(a.getBooks() != null && !a.getBooks().isEmpty()) {
                author.setBooks(a.getBooks().stream().map(b -> new Book(b.getId())).collect(Collectors.toList()));
            }
            return author;
        }).collect(Collectors.toList());
    }
}
