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
    public List<Restaurant> getRestaurants(@RequestParam("region") String region,
                                           @RequestParam("categoryId") Long categoryId) {

        return restaurantService.getRestaurants(region, categoryId);
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant getDetail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

}
