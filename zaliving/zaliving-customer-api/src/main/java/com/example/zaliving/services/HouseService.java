package com.example.zaliving.services;

import com.example.zaliving.domains.House;
import com.example.zaliving.repositories.HouseRepository;
import org.apache.catalina.util.ErrorPageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    public List<House> getDetail() {
        return houseRepository.findAll();
    }
}
