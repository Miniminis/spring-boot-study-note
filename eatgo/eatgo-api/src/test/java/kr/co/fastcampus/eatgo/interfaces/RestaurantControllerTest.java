package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1234L, "Bab Zip", "Gongdeock"));
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1234")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bab Zip\"")
                ));
    }

    @Test
    public void detail() throws Exception {
        Restaurant restaurant = new Restaurant(1234L, "Bab Zip", "Gongdeock");
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("KimChi"));
        restaurant.setMenuItems(menuItems);
        given(restaurantService.getRestaurant(1234L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/1234"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1234")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bab Zip\"")
                ))
                .andExpect(content().string(
                        containsString("KimChi")
                ));

        mvc.perform(get("/restaurants/2023"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2023")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Burger\"")
                ));
    }
}