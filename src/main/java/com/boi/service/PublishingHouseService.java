package com.boi.service;

import com.boi.domain.PublishingHouse;
import com.boi.repository.PublishingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void saveAll(List<PublishingHouse> houses) {
        repository.saveAll(houses);
    }
}
