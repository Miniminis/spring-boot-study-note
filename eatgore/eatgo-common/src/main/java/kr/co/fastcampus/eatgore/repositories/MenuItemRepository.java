package kr.co.fastcampus.eatgore.repositories;

import kr.co.fastcampus.eatgore.domains.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByRestaurantId(Long id);

    List<MenuItem> findAllByRestaurantId(Long id);
}
