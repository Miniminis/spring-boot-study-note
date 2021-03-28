package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.MenuItem;
import kr.co.fastcampus.eatgore.repositories.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void initMock() {
        MockitoAnnotations.openMocks(this);
        mockMenuItemRepository();
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem menuKimchi = MenuItem.builder()
                .id(1L)
                .restaurantId(1004L)
                .name("Kimchi")
                .build();
        menuItems.add(menuKimchi);

        given(menuItemRepository.findByRestaurantId(1004L))
                .willReturn(menuItems);
        given(menuItemRepository.findById(1L))
                .willReturn(Optional.ofNullable(menuKimchi));
    }

    @Test
    void getMenuListTest() {
        List<MenuItem> menuItems = menuItemService.getMenuList(1004L);
        MenuItem menuItem = menuItems.get(0);
        assertThat(menuItem.getId()).isEqualTo(1L);
        assertThat(menuItem.getRestaurantId()).isEqualTo(1004L);
        assertThat(menuItem.getName()).isEqualTo("Kimchi");
    }

    @Test
    void bulkUpdate() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().name("Kimchi").build());
        menuItems.add(MenuItem.builder().id(2L).name("BuchimGye").build());
        menuItems.add(MenuItem.builder().id(12L).destroy(true).build());

        menuItemService.bulkUpdate(1L, menuItems);

        verify(menuItemRepository, times(2)).save(any());
        verify(menuItemRepository, times(1)).deleteById(eq(12L));
    }
}