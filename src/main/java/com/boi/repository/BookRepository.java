package com.boi.repository;

import com.boi.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, RepositoryCustomBook {

}
