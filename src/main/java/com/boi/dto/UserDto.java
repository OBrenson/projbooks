package com.boi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserDto extends EntityDto {

    private String name;

    private String password;

    private Set<RoleDto> roles;

    public UserDto(UUID id, String name, Set<RoleDto> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }
}
