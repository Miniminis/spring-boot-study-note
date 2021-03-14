package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.RestaurantService;
import kr.co.fastcampus.eatgore.domains.MenuItem;
import kr.co.fastcampus.eatgore.domains.Restaurant;
import kr.co.fastcampus.eatgore.domains.RestaurantNotFoundException;
import kr.co.fastcampus.eatgore.domains.Review;
import kr.co.fastcampus.eatgore.interfaces.RestaurantController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build());

        restaurants.add(Restaurant.builder()
                .id(2020L)
                .name("Korean Ramen")
                .address("Busan")
                .build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ));
    }

    @Test
    public void detailWithExisted() throws Exception {
        Review review = Review.builder()
                .id(1L)
                .restaurantId(1004L)
                .name("tester")
                .score(3)
                .description("So so...").build();

        MenuItem menuitem = MenuItem.builder()
                .id(1L)
                .restaurantId(1004L)
                .name("Bibim Bob")
                .build();

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .menuItems(Arrays.asList(menuitem))
                .reviews(Arrays.asList(review))
                .build();

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurant/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ))
                .andExpect(content().string(
                        containsString("\"menuItems\":[{\"id\":1,\"restaurantId\":1004,\"name\":\"Bibim Bob\",\"destroy\":false}]")
                ))
                .andExpect(content().string(
                        containsString("\"reviews\":[{\"id\":1,\"restaurantId\":1004,\"name\":\"tester\",\"score\":3,\"description\":\"So so...\"}]")
                ));
    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(365L)).willThrow(new RestaurantNotFoundException(365L));

        mvc.perform(get("/restaurant/365"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Restaurant Not Found")));
    }

}