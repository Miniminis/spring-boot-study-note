package kr.co.fastcampus.eatgore.domains;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findByRestaurantId(Long id);
}
