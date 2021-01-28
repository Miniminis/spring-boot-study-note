package kr.co.fastcampus.eatgore.domains;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    private List<Restaurant> restaurants;

    public RestaurantRepository() {
        restaurants = new ArrayList<Restaurant>();
        Restaurant bobZip = new Restaurant(1004L,"Bob Zip", "Busan");
        Restaurant ramen = new Restaurant(2020L,"Korean Ramen", "Incheon");
        restaurants.add(bobZip);
        restaurants.add(ramen);
    }

    public List<Restaurant> findAll() {
        return restaurants;
    }

    public Restaurant findById(Long id) {
        return restaurants.stream().filter(res -> res.getId().equals(id))
                .findFirst()
                .orElseGet(null);
    }
}
