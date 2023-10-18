package com.boi.service;

import com.boi.domain.Book;
import com.boi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements DataSourceService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findByColumn(String column, Object value, PageRequest pageable) {
        return bookRepository.findByColumn(column, value, pageable);
    }

    public List<Book> findAll(PageRequest pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    public void saveAll(List<Book> books) {
        bookRepository.saveAll(books);
    }
}
