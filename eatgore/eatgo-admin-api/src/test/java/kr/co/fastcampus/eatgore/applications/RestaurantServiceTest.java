package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockRestaurantRepository();
        mockMenuItemsRepository();
        mockReviewRepository();
    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                .id(1L)
                .restaurantId(1004L)
                .name("tester")
                .score(1)
                .description("핵 맛없음. 최악").build());

        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);
    }

    private void mockMenuItemsRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .id(1L)
                .restaurantId(1004L)
                .name("Gook Bob")
                .destroy(false)
                .build());
        given(menuItemRepository.findByRestaurantId(eq(1004L)))
                .willReturn(menuItems);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId()).isEqualTo(1004L);
    }


    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

//        given(menuItemRepository.findByRestaurantId(1004L)).will(invocation -> {
//            List<MenuItem> menuItems = invocation.getArgument(0);
//            restaurant.setMenuItems(menuItems);
//            return menuItems;
//        });

        assertThat(restaurant.getId()).isEqualTo(1004L);
        assertThat(restaurant.getMenuItems().get(0).getName()).isEqualTo("Gook Bob");
        assertThat(restaurant.getReviews().get(0).getDescription()).isEqualTo("핵 맛없음. 최악");
    }

    @Test
    public void getRestaurantWithNotExisted() {
        assertThatThrownBy(() -> {
            restaurantService.getRestaurant(365L);
        }).isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    public void createRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();

        Restaurant created = restaurantService.createRestaurant(restaurant);

        assertThat(created.getId()).isEqualTo(1234L);
    }

    @Test
    public void updateRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        Restaurant updatedRestaurant = Restaurant.builder()
                .id(1004L)
                .name("Ghost House")
                .address("Thailand")
                .build();

        restaurantService.updateRestaurant(1004L, updatedRestaurant);

        assertThat(restaurant.getName()).isEqualTo(updatedRestaurant.getName());

    }

}