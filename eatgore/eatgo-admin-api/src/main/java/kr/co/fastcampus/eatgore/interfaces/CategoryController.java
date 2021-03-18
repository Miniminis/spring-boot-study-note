package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.CategoryService;
import kr.co.fastcampus.eatgore.domains.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody Category category) throws URISyntaxException {
        categoryService.createCategory(category);

        URI location = new URI("/categories");
        return ResponseEntity.created(location).body("{}");
    }
}
