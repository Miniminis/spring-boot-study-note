package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.RestaurantService;
import kr.co.fastcampus.eatgore.domains.MenuItem;
import kr.co.fastcampus.eatgore.domains.MenuItemRepository;
import kr.co.fastcampus.eatgore.domains.Restaurant;
import kr.co.fastcampus.eatgore.domains.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant getDetail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

}
