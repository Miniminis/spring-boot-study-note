package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.applications.ReviewService;
import kr.co.fastcampus.eatgore.domains.Review;
import kr.co.fastcampus.eatgore.domains.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        Review review = Review.builder()
                .id(1L)
                .score(5)
                .description("완전 환상의 맛!")
                .build();

        reviewService.addReview(1L, review);

        verify(reviewRepository, times(1)).save(review);
    }
}