package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {

        OrderDetail order = new OrderDetail();

        order.setOrderAt(LocalDateTime.now());
//        order.setUserId(8L);
//        order.setItemId(5L);

        OrderDetail createdOrder = orderDetailRepository.save(order);
        Assertions.assertNotNull(createdOrder);
    }
    //Hibernate: insert into order_detail (item_id, order_at, user_id) values (?, ?, ?)

    @Test
    public void read() {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(3L);
        Assertions.assertTrue(orderDetail.isPresent());
    }
    //Hibernate: select orderdetai0_.id as id1_1_0_, orderdetai0_.item_id as item_id2_1_0_, orderdetai0_.order_at as order_at3_1_0_, orderdetai0_.user_id as user_id4_1_0_ from order_detail orderdetai0_ where orderdetai0_.id=?
}
