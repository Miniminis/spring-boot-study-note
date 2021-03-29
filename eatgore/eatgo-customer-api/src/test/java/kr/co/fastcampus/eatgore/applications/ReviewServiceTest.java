package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.Review;
import kr.co.fastcampus.eatgore.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addReview() {
        Review madeReview = Review.builder()
                .id(1L)
                .restaurantId(1L)
                .name("testReviewer")
                .score(5)
                .description("완전 환상의 맛!")
                .build();

        Review review = Review.builder()
                .id(1L)
                .score(5)
                .description("완전 환상의 맛!")
                .build();

        given(reviewRepository.save(any())).willReturn(madeReview);
        Review savedReview = reviewService.addReview(1L, review, "testReviewer");

        assertThat(savedReview.getName()).isEqualTo(madeReview.getName());

        verify(reviewRepository).save(any());
    }
}