package kr.co.fastcampus.eatgore.domains;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Long restaurantId;

    private String name;

    @NotNull
    private Integer score;

    @NotNull
    @NotEmpty
    private String description;
}
