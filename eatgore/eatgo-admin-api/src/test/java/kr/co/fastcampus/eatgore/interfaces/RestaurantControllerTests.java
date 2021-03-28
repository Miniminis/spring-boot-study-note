package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.RestaurantService;
import kr.co.fastcampus.eatgore.domains.*;
import kr.co.fastcampus.eatgore.domains.exceptions.RestaurantNotFoundException;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @BeforeEach
    void initMockBeanData() {
        List<Restaurant> restaurants = new ArrayList<>();

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

        Restaurant angelRestaurant = Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .menuItems(Arrays.asList(menuitem))
                .reviews(Arrays.asList(review))
                .build();

        Restaurant ramenRestaurant = Restaurant.builder()
                .id(2020L)
                .name("Korean Ramen")
                .address("Busan")
                .build();

        restaurants.add(angelRestaurant);
        restaurants.add(ramenRestaurant);

        given(restaurantService.getRestaurants()).willReturn(restaurants);
        given(restaurantService.getRestaurant(1004L))
                .willReturn(angelRestaurant);

    }

    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurants?region=busan"))
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
        given(restaurantService.getRestaurant(365L))
                .willThrow(new RestaurantNotFoundException(365L));

        mvc.perform(get("/restaurant/365"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Restaurant Not Found")));
    }

    @Test
    public void createWithValidData() throws Exception {
        given(restaurantService.createRestaurant(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            return Restaurant.builder()
                    .id(1234L)
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .build();
        });

        mvc.perform(post("/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Beryong\"," +
                        "\"address\":\"Busan\"}"))
                .andExpect(status().isOk())
//                .andExpect(header().string("location", "/restaurant/1234"))
                .andExpect(content().string(containsString("\"id\":1234")))
                .andExpect(content().string(containsString("\"name\":\"Beryong\"")));

        verify(restaurantService).createRestaurant(any());
    }

    @Test
    public void createWithEmptyData() throws Exception {
        mvc.perform(post("/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test name\"," +
                        "\"address\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createWithNullData() throws Exception {
        mvc.perform(post("/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":," +
                        "\"address\":}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateWithValidData() throws Exception {
        mvc.perform(patch("/restaurant/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Ghost House\", \"address\":\"Thailand\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(anyLong(), any());
    }

    @Test
    public void updateWithNullOrEmptyData() throws Exception {
        mvc.perform(patch("/restaurant/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":null, \"address\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
}