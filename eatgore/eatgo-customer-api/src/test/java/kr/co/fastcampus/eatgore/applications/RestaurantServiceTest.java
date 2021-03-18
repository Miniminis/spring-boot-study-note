package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

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
        mockMenuItemRepository();
        mockReviewRepository();
    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        Review goodReview = Review.builder()
                .id(1L)
                .restaurantId(1004L)
                .score(5)
                .description("JMT")
                .build();

        Review sosoReview = Review.builder()
                .id(1L)
                .restaurantId(1004L)
                .score(3)
                .description("Not so bad")
                .build();

        Review badReview = Review.builder()
                .id(1L)
                .restaurantId(1004L)
                .score(1)
                .description("Not even worth for 1 star")
                .build();

        reviews.add(goodReview);
        reviews.add(sosoReview);
        reviews.add(badReview);

        given(reviewRepository.findAllByRestaurantId(1004L))
                .willReturn(reviews);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem kimBokBob = MenuItem.builder()
                .id(1L)
                .restaurantId(1004L)
                .name("KimBokBob")
                .build();

        MenuItem riceCake = MenuItem.builder()
                .id(2L)
                .restaurantId(1004L)
                .name("Rice Cake with Carrot inside")
                .build();

        menuItems.add(kimBokBob);
        menuItems.add(riceCake);

        given(menuItemRepository.findAllByRestaurantId(1004L))
                .willReturn(menuItems);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();

        Restaurant busanRestaurant = Restaurant.builder()
                .id(2021L)
                .address("Busan")
                .name("Busan Gookbob")
                .build();

        restaurants.add(restaurant);
        restaurants.add(busanRestaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findAllByAddressContainingAndCategoryId("Busan", 1L))
                .willReturn(restaurants);

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));
    }


    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants("Busan", 1L);
        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId()).isEqualTo(1004L);
    }


    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId()).isEqualTo(1004L);
    }

    @Test
    public void getRestaurantWithNotExisted() {
        assertThatThrownBy(() -> {
            restaurantService.getRestaurant(365L);
        }).isInstanceOf(RestaurantNotFoundException.class);
    }

}