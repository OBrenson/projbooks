package com.boi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto extends EntityDto {

    public AuthorDto(UUID id, String name, List<UUID> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    private UUID id;

    private String name;

    private List<UUID> books;
}