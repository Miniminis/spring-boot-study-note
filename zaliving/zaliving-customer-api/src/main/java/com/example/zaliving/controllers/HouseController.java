package com.example.zaliving.controllers;

import com.example.zaliving.domains.House;
import com.example.zaliving.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping("/v1/house")
    public List<House> detail() {
        return houseService.getDetail();
    }
}
