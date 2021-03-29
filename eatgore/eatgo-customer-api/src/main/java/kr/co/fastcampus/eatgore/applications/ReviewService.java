package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.Review;
import kr.co.fastcampus.eatgore.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Long restaurantId, Review review, String userName) {

        Review newReview = Review.builder()
                .restaurantId(restaurantId)
                .name(userName)
                .score(review.getScore())
                .description(review.getDescription())
                .build();

        return reviewRepository.save(newReview);
    }
}
