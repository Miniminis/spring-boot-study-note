package com.example.zaliving.repositories;

import com.example.zaliving.domains.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
