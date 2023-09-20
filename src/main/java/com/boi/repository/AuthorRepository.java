package com.boi.repository;

import com.boi.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findAllByBooks_Id(UUID id);

    List<Author> findByBooks_Title(String title);

    List<Author> findByNameLike(String name);
}
