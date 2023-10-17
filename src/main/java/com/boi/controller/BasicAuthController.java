package com.boi.controller;

import com.boi.domain.AppUser;
import com.boi.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class BasicAuthController {

    @Autowired
    private AppUserRepository userRepository;

    @GetMapping(path = "/basicauth")
    public AuthBean basicauth() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user =userRepository.findByName(name);
        return new AuthBean(user.getRoles().stream().map(u -> u.getName() + " ").collect(Collectors.joining(" ")));
    }

}
