package com.boi.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class EntitiesDto<T> implements Serializable {

    private List<T> value;

}
