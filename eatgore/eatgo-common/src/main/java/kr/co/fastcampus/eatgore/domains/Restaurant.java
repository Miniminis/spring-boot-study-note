package kr.co.fastcampus.eatgore.domains;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Restaurant {

    @Id
    @GeneratedValue
    @Setter
    private Long id;

    private Long categoryId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String address;

    @Transient
    private List<MenuItem> menuItems;

    @Transient
    private List<Review> reviews;

    public void setInformation(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = new ArrayList<>(reviews);
    }

}
