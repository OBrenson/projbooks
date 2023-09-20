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
@Table(name = "book")
public class Book extends BaseEntity {

    public Book(UUID id) {
        super(id);
    }

    @Column(unique = true)
    private String title;

    @Column
    private int year;

    @ManyToMany(mappedBy = "books",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publishoing_house_id", nullable = false)
    private PublishingHouse publishingHouse;
}
