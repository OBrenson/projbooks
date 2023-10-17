package com.boi.controller;

import com.boi.domain.AppUser;
import com.boi.dto.EntitiesDto;
import com.boi.dto.Mappers;
import com.boi.dto.UserDto;
import com.boi.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository userRepository;

    @GetMapping(value = "get/all")
    public List<UserDto> getGenres() {

        List<AppUser> users = userRepository.findAll();
        return Mappers.mapUsersToDtos(users);
    }

    @PutMapping("put")
    public void putGenres(@RequestBody EntitiesDto<UserDto> dtos) {
        List<AppUser> users = Mappers.mapDtosToUsers(dtos.getValue(), passwordEncoder);
        userRepository.saveAll(users);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        userRepository.deleteById(UUID.fromString(id));
    }
}
