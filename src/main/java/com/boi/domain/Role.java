package com.boi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity {

    public Role(UUID id, String name) {
        super(id);
        this.name = name;
    }

    @Column(nullable = false, unique = true)
    private String name;
}
