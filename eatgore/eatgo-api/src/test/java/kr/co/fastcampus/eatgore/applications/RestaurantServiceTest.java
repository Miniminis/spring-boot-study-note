package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class RestaurantServiceTest {

//    @Autowired
    private RestaurantService restaurantService;

    @BeforeEach
    public void init() {
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
        MenuItemRepository menuItemRepository = new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assertEquals(1004L, restaurant.getId());
    }

}