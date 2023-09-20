package com.boi.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public abstract class EntityDto implements Serializable {
    protected UUID id;
}
