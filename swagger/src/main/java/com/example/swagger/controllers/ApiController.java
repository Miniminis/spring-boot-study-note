package com.example.swagger.controllers;

import com.example.swagger.dtos.UserReq;
import com.example.swagger.dtos.UserRes;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = { "API 정보를 제공하는 Controller" })
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public ResponseEntity getHello() {
        return ResponseEntity.status(HttpStatus.OK).body("hello!");
    }


    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "x", value = "x 값", required = true),
            @ApiImplicitParam(name = "y", value = "y 값", required = true)
    })
    @GetMapping("/plus/{x}")
    public int plus(@PathVariable int x, @RequestParam int y) {
        return x + y;
    }


    @ApiResponses(value = {
        @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일떄"),
        @ApiResponse(code = 401, message = "사용자의 이름이 10자 이상일때")
    })
    @ApiOperation(value = "사용자의 이름과 나이를 echo 하는 method")
    @PostMapping("/user")
    public UserRes user(@RequestBody UserReq userReq) {
        return new UserRes(userReq.getName(), userReq.getAge());
    }


}
