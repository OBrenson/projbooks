package com.boi.repository;

import com.boi.domain.Book;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RepositoryCustomBook {

    List<Book> findByColumn(String column, Object value, PageRequest pageable);
}
