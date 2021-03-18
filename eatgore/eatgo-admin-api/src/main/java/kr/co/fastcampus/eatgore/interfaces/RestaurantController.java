package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.RestaurantService;
import kr.co.fastcampus.eatgore.domains.Region;
import kr.co.fastcampus.eatgore.domains.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping("/restaurant")
    public Restaurant create(@Valid @RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    @PatchMapping("/restaurant/{id}")
    public Restaurant update(@PathVariable Long id, @Valid @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(id, restaurant);
    }

}
