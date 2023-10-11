package com.boi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "author")
public class Author extends BaseEntity {

    public Author(UUID id) {
        super(id);
    }

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}
