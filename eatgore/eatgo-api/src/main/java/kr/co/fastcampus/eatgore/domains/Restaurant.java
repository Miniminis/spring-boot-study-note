package kr.co.fastcampus.eatgore.domains;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String address;

    @Transient
    private List<MenuItem> menuItems;

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    public void setInformation(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
    }
}
