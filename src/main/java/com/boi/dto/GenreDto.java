package com.boi.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GenreDto extends EntityDto {

    public GenreDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;
}
