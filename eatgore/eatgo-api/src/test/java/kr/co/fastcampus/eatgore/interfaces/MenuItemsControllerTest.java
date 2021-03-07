package kr.co.fastcampus.eatgore.interfaces;

import antlr.build.Tool;
import kr.co.fastcampus.eatgore.applications.MenuItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuItemsController.class)
class MenuItemsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService menuItemService;

    @Test
    public void bulkUpate() throws Exception {
        mvc.perform(patch("/restaurant/123/menuItems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"name\":\"AilenFood\"}, {\"name\":\"Gook Bob\"}]"))
                .andExpect(status().isOk());

        verify(menuItemService).bulkUpdate(eq(123L), any());
    }

}