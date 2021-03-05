package kr.co.fastcampus.eatgore.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Builder
@Getter
public class MenuItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;

    private String name;
}
