package com.boi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoleDto extends EntityDto {

    private String name;

    public RoleDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
