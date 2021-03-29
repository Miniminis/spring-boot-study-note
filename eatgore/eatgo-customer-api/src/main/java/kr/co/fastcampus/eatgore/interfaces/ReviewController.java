package kr.co.fastcampus.eatgore.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.fastcampus.eatgore.applications.ReviewService;
import kr.co.fastcampus.eatgore.domains.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurant/{restaurantId}/reviews")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable("restaurantId") Long restaurantId,
            @Valid @RequestBody Review resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();
        String userName = claims.get("userName", String.class);

        Review addedReview = reviewService.addReview(restaurantId, resource, userName);

        URI location = new URI("/restaurant/"+restaurantId+"/reviews/"+addedReview.getId());
        return ResponseEntity.created(location).body("{}");
    }

}
