package kr.co.fastcampus.eatgore.domains;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private String name;

    @Setter
    private String email;

    @Setter
    private String password;

    @Setter
    private Long level = 1L;

    public void deActivate() {
        level = 0L;
    }

    public boolean isActivate() {
        return level > 0;
    }
}
