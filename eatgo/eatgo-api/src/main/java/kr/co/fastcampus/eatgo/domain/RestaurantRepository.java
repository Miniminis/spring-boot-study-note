package kr.co.fastcampus.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    private List<Restaurant> restaurants;

    public RestaurantRepository() {
        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1234L, "Bab Zip", "Incheon"));
        restaurants.add(new Restaurant(2023L, "Cyber Burger", "Busan"));
    }

    public List<Restaurant> list() {
        return restaurants;
    }

    public Restaurant detail(Long id) {
        return restaurants.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }
}
