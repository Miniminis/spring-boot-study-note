package com.example.study.controller.api;

import com.example.study.interfaces.CRUDInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.service.OrderGroupApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupController implements CRUDInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupApiService orderGroupApiService;

    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> req) {
        log.info("orderGroup create : {}", req);
        return orderGroupApiService.create(req);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
        log.info("orderGroup read : {}", id);
        return orderGroupApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> req) {
        log.info("orderGroup update : {}", req);
        return orderGroupApiService.update(req);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        log.info("orderGroup delete : {}", id);
        return orderGroupApiService.delete(id);
    }
}
