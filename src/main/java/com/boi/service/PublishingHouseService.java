package com.boi.service;

import com.boi.domain.PublishingHouse;
import com.boi.repository.PublishingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PublishingHouseService implements DataSourceService {

    private PublishingHouseRepository repository;

    @Autowired
    public PublishingHouseService(PublishingHouseRepository repository) {
        this.repository = repository;
    }

    public List<PublishingHouse> findAll() {
        return repository.findAll();
    }

    public List<PublishingHouse> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public void saveAll(List<PublishingHouse> houses) {
        repository.saveAll(houses);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
