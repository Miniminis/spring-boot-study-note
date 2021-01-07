package com.example.study.controller.api;

import com.example.study.interfaces.CRUDInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.service.ItemApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemController implements CRUDInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiService itemApiService;


    @Override
    @PostMapping("")
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> req) {
        log.info("item create : {}", req);
        return itemApiService.create(req);
    }

    @Override
    @GetMapping("{id}")
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        log.info("item read : {}", id);
        return itemApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> req) {
        log.info("item update : {}", req);
        return itemApiService.update(req);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        log.info("item delete : {}", id);
        return itemApiService.delete(id);
    }
}