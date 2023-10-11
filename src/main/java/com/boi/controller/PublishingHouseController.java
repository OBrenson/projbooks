package com.boi.controller;

import com.boi.domain.PublishingHouse;
import com.boi.dto.EntitiesDto;
import com.boi.dto.Mappers;
import com.boi.dto.PublDto;
import com.boi.repository.PublishingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library/publ")
public class PublishingHouseController {

    @Autowired
    private PublishingHouseRepository repository;

    @GetMapping("/get/all")
    public List<PublDto> getPublishingHouses() {
        List<PublishingHouse> publs = repository.findAll();
        return Mappers.mapPublsToDtos(publs);
    }

    @PutMapping("put")
    public void putPublishingHouses(@RequestBody EntitiesDto<PublDto> houses) {
        List<PublishingHouse> publs = Mappers.mapDtosToPubl(houses.getValue());
        repository.saveAll(publs);
    }
}
