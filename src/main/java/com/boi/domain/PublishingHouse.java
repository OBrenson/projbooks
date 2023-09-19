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
@Table(name = "publishing_house")
public class PublishingHouse extends BaseEntity{

    public PublishingHouse(UUID id) {
        super(id);
    }

    @Column(name = "name", unique = true)
    private String name;
}
