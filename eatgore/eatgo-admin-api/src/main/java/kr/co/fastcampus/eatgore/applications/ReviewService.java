package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.Review;
import kr.co.fastcampus.eatgore.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
