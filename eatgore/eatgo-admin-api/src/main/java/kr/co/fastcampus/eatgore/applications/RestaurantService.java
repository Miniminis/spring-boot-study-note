package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.*;
import kr.co.fastcampus.eatgore.domains.exceptions.RestaurantNotFoundException;
import kr.co.fastcampus.eatgore.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        return restaurant;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        if(restaurant == null) {
            return Restaurant.builder().build();
        }

        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
        Restaurant foundRestaurant = restaurantRepository.findById(id).orElse(null);
        foundRestaurant.setInformation(restaurant);

        return foundRestaurant;
    }
}
