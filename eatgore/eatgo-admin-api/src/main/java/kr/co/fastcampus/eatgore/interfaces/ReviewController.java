package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.ReviewService;
import kr.co.fastcampus.eatgore.domains.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public List<Review> list() {
        return reviewService.getReviews();
    }
}
