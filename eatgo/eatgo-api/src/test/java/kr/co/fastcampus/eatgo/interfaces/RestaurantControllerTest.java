package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import kr.co.fastcampus.eatgo.domain.RestaurantRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @SpyBean(RestaurantService.class)
    private RestaurantService restaurantService;

    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImpl.class)      //test env 에서 필요
    private MenuItemRepository menuItemRepository;

    @Test
    public void list() throws Exception {
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