package com.example.study.controller;

import com.example.study.interfaces.CRUDInterface;
import com.example.study.model.network.Header;
import com.example.study.service.BaseApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@Slf4j
public abstract class BaseCRUDController<Req, Res, Entity> implements CRUDInterface<Req, Res> {

    @Autowired(required = false)
    protected BaseApiService<Req, Res, Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> req) {
        log.info("create :::: {}", req);       //log 관리. {} -> req.toString()
        return baseService.create(req);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        log.info("read :::: {}", id);
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> req) {
        log.info("update :::: {}", req);
        return baseService.update(req);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        log.info("delete :::: {}", id);
        return baseService.delete(id);
    }
}
