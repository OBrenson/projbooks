package com.boi.repository;

import com.boi.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    public AppUser findByName(String name);
}
