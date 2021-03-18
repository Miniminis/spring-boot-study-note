package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.Category;
import kr.co.fastcampus.eatgore.domains.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
