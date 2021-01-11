package com.example.study.controller.api;

import com.example.study.controller.BaseCRUDController;
import com.example.study.interfaces.CRUDInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseCRUDController<UserApiRequest, UserApiResponse, User> {

    @GetMapping("")
    public Header<List<UserApiResponse>> search(@PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("{}", pageable);
        return ((UserApiService)baseService).search(pageable);
    }

}
