package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.MenuItemService;
import kr.co.fastcampus.eatgore.domains.MenuItem;
import kr.co.fastcampus.eatgore.domains.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemsController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("/restaurant/{restaurantId}/menuItems")
    public List<MenuItem> list(@PathVariable Long restaurantId) {
        return menuItemService.getMenuList(restaurantId);
    }

    @PatchMapping("/restaurant/{restaurantId}/menuItems")
    public List<MenuItem> bulkUpdate(@PathVariable("restaurantId") Long restaurantId,
                                 @RequestBody List<MenuItem> menuItems) {
        return menuItemService.bulkUpdate(restaurantId, menuItems);
    }

}
