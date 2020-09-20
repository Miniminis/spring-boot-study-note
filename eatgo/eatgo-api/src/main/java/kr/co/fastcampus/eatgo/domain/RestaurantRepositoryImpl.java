package kr.co.fastcampus.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants;

    public RestaurantRepositoryImpl() {
        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1234L, "Bab Zip", "Incheon"));
        restaurants.add(new Restaurant(2023L, "Cyber Burger", "Busan"));
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurants.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }
}
