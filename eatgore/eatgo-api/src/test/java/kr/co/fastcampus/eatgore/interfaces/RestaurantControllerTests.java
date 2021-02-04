package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.RestaurantService;
import kr.co.fastcampus.eatgore.domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Restaurant bobZip = new Restaurant(1004L, "Bob Zip", "Busan");
        ramen = new Restaurant(2020L, "Korean Ramen", "Incheon");
        ramen.addMenuItem(new MenuItem("Kimchi"));
        restaurants.add(bobZip);
        restaurants.add(ramen);
    }

    @Test
    public void list() throws Exception {
        Mockito.when(restaurantService.getRestaurants()).thenReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"Bob Zip\"")));
    }

    @Test
    public void detail() throws Exception {
        Mockito.when(restaurantService.getRestaurant(2020L)).thenReturn(ramen);

        mvc.perform(get("/restaurant/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2020")))
                .andExpect(content().string(containsString("\"name\":\"Korean Ramen\"")))
                .andExpect(content().string(containsString("Kimchi")));
    }
}