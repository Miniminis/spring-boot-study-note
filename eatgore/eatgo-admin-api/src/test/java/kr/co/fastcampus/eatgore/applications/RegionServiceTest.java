package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.Region;
import kr.co.fastcampus.eatgore.domains.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class RegionServiceTest {

    @InjectMocks
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @BeforeEach
    void initMock() {
        MockitoAnnotations.openMocks(this);
        mockInitRegionRepository();
    }

    private void mockInitRegionRepository() {
        List<Region> regions = new ArrayList<>();
        Region region = Region.builder()
                .id(1L)
                .name("Busan")
                .build();
        regions.add(region);

        given(regionRepository.findAll()).willReturn(regions);
    }

    @Test
    void getListTest() {
        List<Region> regions =  regionService.getList();
        Region region = regions.get(0);

        assertThat(region.getName()).isEqualTo("Busan");

        verify(regionRepository).findAll();
    }

    @Test
    void addRegionTest() {
        given(regionRepository.save(any())).will(invocation -> {
            Region region = invocation.getArgument(0);
            region.setId(1L);
            return region;
        });

        Region region = Region.builder()
                .id(1L)
                .name("Busan")
                .build();

        Region newRegion = regionService.addRegion(region);

        assertThat(newRegion.getId()).isEqualTo(1L);
        assertThat(newRegion.getName()).isEqualTo("Busan");
    }
}