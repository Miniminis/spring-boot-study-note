package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setStatus("W");
        item.setName("LG 그램 2021년 형");
        item.setTitle("[신년세일] LG 그램 2021년 형");
        item.setContent("최고급 사양에 대박 가격");
        item.setPrice(BigDecimal.valueOf(1000000));
        item.setBrandName("LG");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("TestServer");
//        item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
    }

    @Test
    public void read() {
        Optional<Item> item = itemRepository.findById(6L);

        item.ifPresent(selectedItem -> {
            System.out.println("selectedItem ::::  "+selectedItem);
        });

        Assertions.assertTrue(item.isPresent());
//        Hibernate: select item0_.id as id1_0_0_, item0_.content as content2_0_0_, item0_.name as name3_0_0_, item0_.price as price4_0_0_ from item item0_ where item0_.id=?
    }
}
