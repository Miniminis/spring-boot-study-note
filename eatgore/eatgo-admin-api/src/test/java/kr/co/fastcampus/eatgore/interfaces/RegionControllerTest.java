package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.RegionService;
import kr.co.fastcampus.eatgore.domains.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegionController.class)
class RegionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegionService regionService;

    @BeforeEach
    void init() {
        List<Region> regions = new ArrayList<>();
        Region busan = Region.builder().name("Busan").build();
        Region ulsan = Region.builder().name("ulsan").build();

        regions.add(busan);
        regions.add(ulsan);

        given(regionService.getList()).willReturn(regions);
    }

    @Test
    void listTest() throws Exception {
        mvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Busan\"")));

        verify(regionService).getList();
    }

    @Test
    void createTest() throws Exception {
        Region busan = Region.builder().id(1L).name("Busan").build();

        mvc.perform(post("/regions/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("{}")));
    }
}