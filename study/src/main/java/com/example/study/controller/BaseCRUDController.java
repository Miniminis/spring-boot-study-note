package com.example.study.controller;

import com.example.study.interfaces.CRUDInterface;
import com.example.study.model.network.Header;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@Slf4j
public class BaseCRUDController<Req, Res> implements CRUDInterface<Req, Res> {

    protected CRUDInterface<Req, Res> baseService;

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
