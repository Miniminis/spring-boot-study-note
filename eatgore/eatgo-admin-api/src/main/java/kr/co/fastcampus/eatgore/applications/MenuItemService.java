package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.*;
import kr.co.fastcampus.eatgore.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getMenuList(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public List<MenuItem> bulkUpdate(long restaurantId, List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            updateMenu(restaurantId, menuItem);
        }

        return menuItems;
    }

    private void updateMenu(long restaurantId, MenuItem menuItem) {
        if (menuItem.isDestroy()) {
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }
}
