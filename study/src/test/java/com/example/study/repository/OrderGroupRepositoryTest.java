package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.enumclass.OrderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create() {
        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus("R");
        orderGroup.setOrderType(OrderType.EACH);
        orderGroup.setRevAddress("서울시 강남구 양재천로 4다길");
        orderGroup.setRevName("테스터1");
        orderGroup.setPaymentType("Card");
        orderGroup.setTotalPrice(BigDecimal.valueOf(200000));
        orderGroup.setTotalQuantity(3);
        orderGroup.setOrderAt(LocalDateTime.now());
        orderGroup.setArrivalDate(LocalDateTime.now());
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("TestServer");
//        orderGroup.setUserId(1L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assertions.assertNotNull(newOrderGroup);
    }
}
