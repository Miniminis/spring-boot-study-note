package com.fastcampus.javaallinone.project3.mycontact.domain.dto;

import java.time.LocalDate;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Martin
 * @since 2019-08-21
 */
@Embeddable
@Data
@NoArgsConstructor
public class Birthday {
    private Integer yearOfBirthday;
    private Integer monthOfBirthday;
    private Integer dayOfBirthday;

    private Birthday(LocalDate birthday) {
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.dayOfBirthday = birthday.getDayOfMonth();
    }

    public static Birthday of(LocalDate birthday) {
        return new Birthday(birthday);
    }
}
