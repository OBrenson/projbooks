package com.boi.service;

import com.boi.domain.Author;
import com.boi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService implements DataSourceService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll(PageRequest pageRequest) {
        return authorRepository.findAll(pageRequest).getContent();
    }

    public Author findById(UUID id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> findByNameLike(String name) {
        return authorRepository.findByNameLike("%" + name + "%");
    }

    public List<Author> findAllByBooks_Id(UUID id) {
        return authorRepository.findAllByBooks_Id(id);
    }

    public void saveAll(List<Author> authors) {
        authorRepository.saveAll(authors);
    }
}