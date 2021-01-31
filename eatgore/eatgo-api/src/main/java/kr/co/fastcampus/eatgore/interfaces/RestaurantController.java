package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.domains.MenuItem;
import kr.co.fastcampus.eatgore.domains.MenuItemRepository;
import kr.co.fastcampus.eatgore.domains.Restaurant;
import kr.co.fastcampus.eatgore.domains.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant getDetail(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);
        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        return restaurant;
    }

}
