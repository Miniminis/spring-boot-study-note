package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.domains.Restaurant;
import kr.co.fastcampus.eatgore.domains.RestaurantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController() {
        this.restaurantRepository = new RestaurantRepository();
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant getDetail(@PathVariable("id") Long id) {
        return restaurantRepository.findById(id);
    }

}
