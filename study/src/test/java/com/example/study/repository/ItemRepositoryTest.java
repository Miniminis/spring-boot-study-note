package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setName("엘지 노트북");
        item.setPrice(1000000);
        item.setContent("최고급 사양에 대박 가격");

        itemRepository.save(item);
        Assertions.assertNotNull(item);
    }

    //Hibernate: insert into item (content, name, price) values (?, ?, ?)

    @Test
    public void read() {
        Optional<Item> item = itemRepository.findById(1L);

//        item.ifPresent(selectedItem -> {
//            System.out.println(selectedItem);
//        });

        Assertions.assertTrue(item.isPresent());
//        Hibernate: select item0_.id as id1_0_0_, item0_.content as content2_0_0_, item0_.name as name3_0_0_, item0_.price as price4_0_0_ from item item0_ where item0_.id=?
    }
}
