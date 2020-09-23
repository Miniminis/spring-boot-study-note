package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;        //junit4
import org.junit.jupiter.api.BeforeEach; //junit 5
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @BeforeEach     //junit4:@Before --> junit5:@BeforeEach
    public void setUp() {
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
        MenuItemRepository menuItemRepository = new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        assertThat(restaurants.get(0).getId(), is(1234L));
    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1234L);
        assertThat(restaurant.getId(), is(1234L));
    }
}