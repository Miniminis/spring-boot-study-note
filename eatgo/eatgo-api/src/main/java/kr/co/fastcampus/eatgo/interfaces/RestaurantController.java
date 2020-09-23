package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import kr.co.fastcampus.eatgo.domain.RestaurantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired      //의존성 주입을 SpringFramework 에 맡김
    private RestaurantRepository repository;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        return repository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        Restaurant restaurant = repository.findById(id);

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("KimChi"));
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }

}
