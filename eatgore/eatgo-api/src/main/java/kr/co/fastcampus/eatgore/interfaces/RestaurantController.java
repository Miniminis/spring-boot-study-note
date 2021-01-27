package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.domains.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList();
        Restaurant restaurant = new Restaurant(1004L,"Bob Zip", "Busan");
        restaurants.add(restaurant);
        return restaurants;
    }

}
