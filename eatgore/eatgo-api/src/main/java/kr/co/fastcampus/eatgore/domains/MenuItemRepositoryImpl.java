package kr.co.fastcampus.eatgore.domains;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {

    private List<MenuItem> menuItems;

    public MenuItemRepositoryImpl() {
        this.menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Kimchi"));
    }

    @Override
    public List<MenuItem> findByRestaurantId(Long id) {
        return menuItems;
    }
}
