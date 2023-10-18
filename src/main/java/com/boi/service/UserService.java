package com.boi.service;

import com.boi.domain.AppUser;
import com.boi.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements DataSourceService {

    private AppUserRepository userRepository;

    @Autowired
    public UserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUser findByName(String name) {
        return userRepository.findByName(name);
    }

    public void saveAll(List<AppUser> users) {
        userRepository.saveAll(users);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
