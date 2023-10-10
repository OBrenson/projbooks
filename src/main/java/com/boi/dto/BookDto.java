package com.boi.dto;

import com.boi.domain.PublishingHouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookDto extends EntityDto {

    public BookDto(UUID id, String tittle, GenreDto genre, PublDto publishingHouse, List<AuthorDto> authors) {
        this.id = id;
        this.title = tittle;
        this.genre = genre;
        this.publ = publishingHouse;
        this.authors = authors;
    }

    private String title;

    private GenreDto genre;

    private PublDto publ;

    private List<AuthorDto> authors;
}
