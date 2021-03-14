package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.MenuItemService;
import kr.co.fastcampus.eatgore.domains.MenuItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuItemsController.class)
class MenuItemsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService menuItemService;

    @Test
    public void list() throws Exception {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem fireDuckRice = MenuItem.builder()
                .id(1L)
                .restaurantId(1004L)
                .name("Fire duck Rice")
                .destroy(false)
                .build();

        menuItems.add(fireDuckRice);

        given(menuItemService.getMenuList(1004L))
                .willReturn(menuItems);

        mvc.perform(get("/restaurant/1004/menuItems"))
                .andExpect(content().string(
                        containsString("\"id\":1")
                ))
                .andExpect(content().string(
                        containsString("\"restaurantId\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Fire duck Rice\"")
                ));
    }

    @Test
    public void bulkUpate() throws Exception {
        mvc.perform(patch("/restaurant/123/menuItems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"name\":\"AilenFood\"}, {\"name\":\"Gook Bob\"}]"))
                .andExpect(status().isOk());

        verify(menuItemService).bulkUpdate(eq(123L), any());
    }

}