package com.boi.repository;

import com.boi.domain.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, UUID> {

}
