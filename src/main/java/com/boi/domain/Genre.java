package com.boi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "genre")
public class Genre extends BaseEntity {

    public Genre(UUID id) {
        super(id);
    }

    @Column(unique = true)
    private String name;
}
