package com.example.study.controller.api;

import com.example.study.interfaces.CRUDInterface;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/")
public class UserController implements CRUDInterface {

    @Override
    public Header create() {
        return null;
    }

    @Override
    public Header read(Long id) {
        return null;
    }

    @Override
    public Header update() {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
