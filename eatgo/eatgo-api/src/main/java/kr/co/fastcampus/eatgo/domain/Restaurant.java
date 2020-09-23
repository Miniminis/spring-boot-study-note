package kr.co.fastcampus.eatgo.domain;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private final Long id;
    private final String name;
    private final String address;
    private List<MenuItem> menuItems;

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.menuItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getInformation() {
        return name + " in " + address;
    }

    public List<MenuItem> getMenuItems() { return this.menuItems; }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
