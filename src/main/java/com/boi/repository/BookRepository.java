package com.boi.repository;

import com.boi.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Book findByTitle(String title);

    Book findByYear(int year);

    List<Book> findByAuthors_Id(UUID id);

    List<Book> findByAuthors_Name(String name);

    List<Book> findByGenre_Name(String genreName);

    List<Book> findByPublishingHouse_Id(UUID id);

    List<Book> findByPublishingHouse_Name(String name);
}
