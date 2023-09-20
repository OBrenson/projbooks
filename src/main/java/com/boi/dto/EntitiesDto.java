package com.boi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EntitiesDto<T extends EntityDto> implements Serializable {

    public EntitiesDto(List<T> value) {
        this.value = value;
    }

    private List<T> value;

}
