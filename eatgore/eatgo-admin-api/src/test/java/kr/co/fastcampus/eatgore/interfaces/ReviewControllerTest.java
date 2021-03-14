package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.ReviewService;
import kr.co.fastcampus.eatgore.domains.Review;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @BeforeEach
    void initMockBean() {
        List<Review> reviews = new ArrayList<>();

        Review review= Review.builder()
                .id(1L)
                .name("stranger")
                .score(5)
                .description("Awesome")
                .build();

        reviews.add(review);

        given(reviewService.getReviews())
                .willReturn(reviews);
    }

    @Test
    void listTest() throws Exception {
        mvc.perform(get("/reviews"))
                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"name\":\"stranger\"")))
                .andExpect(content().string(containsString("\"score\":5")))
                .andExpect(content().string(containsString("\"description\":\"Awesome\"")));
    }

}