package com.example.study.service;

import com.example.study.model.entity.OrderDetail;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserApiService extends BaseApiService<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private OrderGroupApiService orderGroupApiService;

    @Autowired
    private ItemApiService itemApiService;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        //1. request data
        UserApiRequest userApiRequest = request.getData();

        //2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.UNREGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        //3. response user
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(user -> Header.OK(response(user)))
                .orElseGet(() -> Header.ERROR("No Matched User"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //3. data update
        //4. reponse

        //1. data
        UserApiRequest userApiRequest = request.getData();

        //2. id -> user read
        Optional<User> optionalUser = baseRepository.findById(userApiRequest.getId());

        return optionalUser
                .map(user -> {
                    user.setAccount(userApiRequest.getAccount())
                            .setPassword(userApiRequest.getPassword())
                            .setEmail(userApiRequest.getEmail())
                            .setPhoneNumber(userApiRequest.getPhoneNumber())
                            .setStatus(userApiRequest.getStatus())
                            .setRegisteredAt(userApiRequest.getRegisteredAt())
                            .setUnregisteredAt(userApiRequest.getUnRegisteredAt());
                    return user;
                })
                .map(updateUser -> baseRepository.save(updateUser))
                .map(user -> Header.OK(response(user)))
                .orElseGet(() -> Header.ERROR("No Matched User!"));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> optionalUser = baseRepository.findById(id);
        return optionalUser.map(user -> {
            baseRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("Something went wrong!"));
    }

    public UserApiResponse response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unRegisteredAt(user.getUnregisteredAt())
                .build();
        return userApiResponse;
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        log.info("{}", pageable);

        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponses = users.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return Header.OK(userApiResponses, pagination);
    }

    public Header<UserOrderApiResponse> orderInfo(Long id) {
        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        List<OrderGroup> orderGroups = user.getOrderGroups();
        List<OrderGroupApiResponse> orderGroupApiResponses = orderGroups.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiService.response(orderGroup).getData();

                    List<ItemApiResponse> itemApiResponses = orderGroup.getOrderDetails().stream()
                            .map(OrderDetail::getItem)
                            .map(item -> itemApiService.response(item).getData())
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponses(itemApiResponses);
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());


        userApiResponse.setOrderGroupApiResponses(orderGroupApiResponses);

        UserOrderApiResponse userOrderApiResponse = UserOrderApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderApiResponse);
    }
}
