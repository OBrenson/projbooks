package com.boi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PublDto extends EntityDto {

    private String name;

    public PublDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
