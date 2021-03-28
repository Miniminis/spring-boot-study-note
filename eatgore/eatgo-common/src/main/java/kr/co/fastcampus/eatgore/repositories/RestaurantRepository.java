package kr.co.fastcampus.eatgore.repositories;

import kr.co.fastcampus.eatgore.domains.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    List<Restaurant> findAllByAddressContainingAndCategoryId(String region, Long categoryId);
}
