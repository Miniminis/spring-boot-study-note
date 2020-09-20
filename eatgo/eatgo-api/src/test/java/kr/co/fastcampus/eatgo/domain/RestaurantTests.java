package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

class RestaurantTests {

    @Test
    public void creation() {
        Restaurant restaurant = new Restaurant(1004L, "Bob Zip", "Busan");
        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getName(), is("Bob Zip"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }

    @Test
    public void information() {
        Restaurant restaurant = new Restaurant(1004L,"Bob Zip", "Seoul");
        assertThat(restaurant.getInformation(), is("Bob Zip in Seoul"));
    }

    @AfterEach
    void tearDown() {
    }
}