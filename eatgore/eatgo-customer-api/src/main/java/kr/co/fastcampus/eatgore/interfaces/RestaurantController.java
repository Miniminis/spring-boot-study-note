package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.RestaurantService;
import kr.co.fastcampus.eatgore.domains.Region;
import kr.co.fastcampus.eatgore.domains.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    private static final Logger logger = LogManager.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(@RequestParam("region") String region,
                                           @RequestParam("categoryId") Long categoryId) {

        logger.info("getRestaurants + {}, {}", region, categoryId);
        logger.info("INFO SUCCESS");
        logger.debug("DEBUG SUCCESS");
        logger.error("ERROR SUCCESS");


        List<Restaurant> restaurants = restaurantService.getRestaurants(region, categoryId);

        logger.info("getRestaurants + {}", restaurants.toString());

        return restaurants;
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant getDetail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

}
