package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.MenuItem;
import kr.co.fastcampus.eatgore.domains.MenuItemRepository;
import kr.co.fastcampus.eatgore.domains.Restaurant;
import kr.co.fastcampus.eatgore.domains.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        if (restaurant != null) {
            List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
            restaurant.setMenuItems(menuItems);
        }

        return restaurant;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        if(restaurant == null) {
            return new Restaurant();
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
