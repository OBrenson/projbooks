package com.boi.controller;

import com.boi.domain.Genre;
import com.boi.domain.PublishingHouse;
import com.boi.dto.EntitiesDto;
import com.boi.dto.Mappers;
import com.boi.dto.PublDto;
import com.boi.repository.PublishingHouseRepository;
import com.boi.service.PublishingHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library/publ")
public class PublishingHouseController {

    @Autowired
    private PublishingHouseService service;

    @GetMapping("/get/all")
    public List<PublDto> getPublishingHouses(@RequestParam(required = false) Optional<String> sortBy,
                                             @RequestParam(required = false) Optional<Boolean> isDesc) {
        Sort sort = ControllerUtils.getSort(sortBy, isDesc);
        List<PublishingHouse> publs;
        if(sort == null) {
            publs = service.findAll();
        } else {
            publs = service.findAll(sort);
        }
        return Mappers.mapPublsToDtos(publs);
    }

    @PutMapping("put")
    public void putPublishingHouses(@RequestBody EntitiesDto<PublDto> houses) {
        List<PublishingHouse> publs = Mappers.mapDtosToPubl(houses.getValue());
        service.saveAll(publs);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.deleteById(UUID.fromString(id));
    }
}
