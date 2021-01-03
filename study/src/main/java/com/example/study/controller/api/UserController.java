package com.example.study.controller.api;

import com.example.study.interfaces.CRUDInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController implements CRUDInterface<UserApiRequest, UserApiResponse> {

    /*
     * create //post, ""
     * read  //get, {id}
     * update    //put, ""
     * delete    //delete, {id}
     * */

    @Autowired
    private UserApiService userApiService;

    @Override
    @PostMapping("")
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> userApiRequest) {
        log.info("{}", userApiRequest);       //log 관리. {} -> userApiRequest.toString()
        return userApiService.create(userApiRequest);
    }

    @Override
    @GetMapping("{id}")
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("read: {}", id);
        return userApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> userApiRequest) {
        log.info("update : {}", userApiRequest);
        return userApiService.update(userApiRequest);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<UserApiResponse> delete(@PathVariable Long id) {
        return null;
    }
}
