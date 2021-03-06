package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.RestaurantService;
import kr.co.fastcampus.eatgore.domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    private List<Restaurant> restaurants;
    private Restaurant ramen;

    @BeforeEach
    public void init() {
        restaurants = new ArrayList<>();
        Restaurant bobZip = Restaurant.builder()
                .id(1L)
                .name("Bob Zip")
                .address("Busan")
                .build();

        ramen = Restaurant.builder()
                .id(2L)
                .name("Korean Ramen")
                .address("Incheon")
                .build();

        ramen.addMenuItem(MenuItem.builder().id(1L).restaurantId(2L).name("Kimchi").build());

        restaurants.add(bobZip);
        restaurants.add(ramen);
    }

    @Test
    public void list() throws Exception {
        Mockito.when(restaurantService.getRestaurants()).thenReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"name\":\"Bob Zip\"")));
    }

    @Test
    public void detail() throws Exception {
        Mockito.when(restaurantService.getRestaurant(2L)).thenReturn(ramen);

        mvc.perform(get("/restaurant/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2")))
                .andExpect(content().string(containsString("\"name\":\"Korean Ramen\"")))
                .andExpect(content().string(containsString("Kimchi")));
    }

    @Test
    public void create() throws Exception {
        given(restaurantService.createRestaurant(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            return new Restaurant(1L, restaurant.getName(), restaurant.getAddress());
        });

        mvc.perform(post("/restaurant/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                    "            \"name\" : \"Alien Food\",\n" +
                    "            \"address\" : \"Universe\"\n" +
                    "        }"))
                .andExpect(status().isOk())
//                .andExpect(header().string("location", "/restaurant/1"))
                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"name\":\"Alien Food\"")));

        verify(restaurantService).createRestaurant(any());
    }


}