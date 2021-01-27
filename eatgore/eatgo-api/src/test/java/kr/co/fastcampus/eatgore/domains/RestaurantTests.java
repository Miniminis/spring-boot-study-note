package kr.co.fastcampus.eatgore.domains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantTests {

    @Test
    public void create() {
        Restaurant restaurant = new Restaurant("Bob Zip", "Busan");
        Assertions.assertEquals("Bob Zip", restaurant.getName());
        Assertions.assertEquals("Busan", restaurant.getAddress());
    }

    @Test
    public void info() {
        Restaurant restaurant = new Restaurant("Bob Zip", "Seoul");
        assertEquals("Bob Zip in Seoul", restaurant.getInformation());
    }

}