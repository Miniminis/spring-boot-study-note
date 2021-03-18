package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.CategoryService;
import kr.co.fastcampus.eatgore.domains.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @BeforeEach
    void initMockBean() {
        List<Category> categories = new ArrayList<>();
        Category category = Category.builder()
                .id(1L)
                .name("Asian")
                .build();
        categories.add(category);

        given(categoryService.getCategories()).willReturn(categories);
    }

    @Test
    void 카테고리_목록롤_컨트롤() throws Exception {
        mvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"name\":\"Asian\"")));

        verify(categoryService).getCategories();
    }

    @Test
    void 카테고리_생성_컨트롤러() throws Exception {
        mvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Asian\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("{}")));
    }
}