package com.boi.controller;

import com.boi.domain.AppUser;
import com.boi.dto.EntitiesDto;
import com.boi.dto.Mappers;
import com.boi.dto.UserDto;
import com.boi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    private UserService userService;

    @GetMapping(value = "get/all")
    @Secured("ADMIN")
    public List<UserDto> getUsers() {

        List<AppUser> users = userService.findAll();
        return Mappers.mapUsersToDtos(users);
    }

    @PutMapping("put")
    @Secured("ADMIN")
    public void putUsers(@RequestBody EntitiesDto<UserDto> dtos) {
        List<AppUser> users = Mappers.mapDtosToUsers(dtos.getValue(), passwordEncoder);
        userService.saveAll(users);
    }

    @DeleteMapping("{id}")
    @Secured("ADMIN")
    public void delete(@PathVariable String id) {
        userService.deleteById(UUID.fromString(id));
    }
}
