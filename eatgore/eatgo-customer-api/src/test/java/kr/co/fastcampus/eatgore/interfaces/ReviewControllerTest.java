package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.ReviewService;
import kr.co.fastcampus.eatgore.domains.Review;
import kr.co.fastcampus.eatgore.interfaces.ReviewController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void createWithValidData() throws Exception {
        given(reviewService.addReview(eq(123L), any()))
                .willReturn(Review.builder()
                        .id(1L)
                        .restaurantId(123L)
                        .name("mhson")
                        .score(4)
                        .description("존맛탱")
                        .build());

        mvc.perform(post("/restaurant/123/reviews")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"name\": \"mhson\",\n" +
                            "    \"score\": 5,\n" +
                            "    \"description\": \"완전맛있어요!\"\n" +
                            "}"))
                    .andExpect(header().string("location", "/restaurant/123/reviews/1"))
                    .andExpect(status().isCreated());

        verify(reviewService).addReview(eq(123L), any());
    }

    @Test
    public void createWithInvalidData() throws Exception {
        mvc.perform(post("/restaurant/123/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(eq(123L), any());
    }
}