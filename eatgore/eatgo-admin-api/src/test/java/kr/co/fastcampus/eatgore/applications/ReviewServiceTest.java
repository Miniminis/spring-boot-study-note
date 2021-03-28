package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.Review;
import kr.co.fastcampus.eatgore.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockReviewRepository();
    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        Review review= Review.builder()
                .id(1L)
                .name("stranger")
                .score(5)
                .description("Awesome")
                .build();

        Review anotherReview = Review.builder()
                .id(2L)
                .name("passenger")
                .score(2)
                .description("Terrible")
                .build();

        reviews.add(review);
        reviews.add(anotherReview);

        given(reviewRepository.findAll()).willReturn(reviews);
    }

    @Test
    void getReviewTest() {
        List<Review> reviews = reviewService.getReviews();
        Review review = reviews.get(0);
        assertThat(review.getId()).isEqualTo(1L);
        assertThat(review.getName()).isEqualTo("stranger");
        assertThat(review.getScore()).isEqualTo(5);
        assertThat(review.getDescription()).isEqualTo("Awesome");
    }

}