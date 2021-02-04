package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.RestaurantService;
import kr.co.fastcampus.eatgore.domains.MenuItemRepository;
import kr.co.fastcampus.eatgore.domains.MenuItemRepositoryImpl;
import kr.co.fastcampus.eatgore.domains.RestaurantRepository;
import kr.co.fastcampus.eatgore.domains.RestaurantRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @SpyBean
    private RestaurantService restaurantService;

    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;

    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"Bob Zip\"")));
    }

    @Test
    public void detail() throws Exception {
        mvc.perform(get("/restaurant/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2020")))
                .andExpect(content().string(containsString("\"name\":\"Korean Ramen\"")))
                .andExpect(content().string(containsString("Kimchi")));
    }
}