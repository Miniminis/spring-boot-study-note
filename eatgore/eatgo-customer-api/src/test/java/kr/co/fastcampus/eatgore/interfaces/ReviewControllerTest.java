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
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsInVzZXJOYW1lIjoidGVzdGVyIn0.Q-dpPeV_d4nIHh_kyKkF6X8AnGyXHBsZ2fKo_4cDwNc";

        Review review = Review.builder()
                .score(5)
                .description("완전맛있어요!")
                .build();

        given(reviewService.addReview(123L, review, "tester"))
                .willReturn(Review.builder().id(1L).build());

        mvc.perform(post("/restaurant/123/reviews")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"score\":5, \"description\":\"완전맛있어요!\"}"))
                    .andExpect(status().isCreated())
                    .andExpect(header().string("location", "/restaurant/123/reviews/1"));

        verify(reviewService).addReview(123L, review, "tester");
    }


    @Test
    public void createWithInvalidData() throws Exception {
        mvc.perform(post("/restaurant/123/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(eq(123L), any(), any());
    }
}