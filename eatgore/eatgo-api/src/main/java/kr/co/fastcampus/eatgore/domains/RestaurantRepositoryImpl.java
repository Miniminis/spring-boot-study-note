package kr.co.fastcampus.eatgore.domains;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants;

    public RestaurantRepositoryImpl() {
        restaurants = new ArrayList<Restaurant>();
        Restaurant bobZip = new Restaurant(1004L, "Bob Zip", "Busan");
        Restaurant ramen = new Restaurant(2020L, "Korean Ramen", "Incheon");
        restaurants.add(bobZip);
        restaurants.add(ramen);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurants.stream().filter(res -> res.getId().equals(id))
                .findFirst()
                .orElseGet(null);
    }
}
