package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.Category;
import kr.co.fastcampus.eatgore.domains.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
        mockCategoryRepository();
    }

    private void mockCategoryRepository() {
        List<Category> categories =  new ArrayList<>();

        Category category = Category.builder()
                .id(1L)
                .name("Asian")
                .build();
        categories.add(category);

        given(categoryRepository.findAll()).willReturn(categories);
        given(categoryRepository.save(any())).will(invocation -> invocation.getArgument(0));
    }

    @Test
    void 카테고리_목록_서비스() {
        List<Category> categories = categoryService.getCategories();
        Category category = categories.get(0);

        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isEqualTo("Asian");

        verify(categoryRepository).findAll();
    }

    @Test
    void 카테고리_생성_서비스() {
        Category category = Category.builder()
                .name("Asian")
                .build();

        Category newCategory = categoryService.createCategory(category);

        verify(categoryRepository).save(any());

        assertThat(newCategory.getName()).isEqualTo("Asian");
    }

}