package kr.co.fastcampus.eatgore.repositories;

import kr.co.fastcampus.eatgore.domains.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByRestaurantId(Long id);

}
