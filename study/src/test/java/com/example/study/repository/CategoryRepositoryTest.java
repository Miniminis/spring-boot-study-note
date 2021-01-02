package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void create() {
        Category category = Category.builder()
                .type("FOOD")
                .title("KOREAN")
                .build();

        Category newCategory = categoryRepository.save(category);
        Assertions.assertNotNull(newCategory);
    }
}
