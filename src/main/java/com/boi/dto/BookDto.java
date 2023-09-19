package com.boi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookDto implements Serializable {

    public BookDto(String tittle, UUID genreId, UUID publishingHouseId, List<UUID> authors) {
        this.tittle = tittle;
        this.genreId = genreId;
        this.publishingHouseId = publishingHouseId;
        this.authors = authors;
    }

    private UUID id;

    private String tittle;

    private UUID genreId;

    private UUID publishingHouseId;

    List<UUID> authors;
}
