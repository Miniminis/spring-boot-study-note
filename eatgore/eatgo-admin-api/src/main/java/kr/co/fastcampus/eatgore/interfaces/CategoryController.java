package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.CategoryService;
import kr.co.fastcampus.eatgore.domains.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list() {
        return categoryService.getCategories();
    }
}
