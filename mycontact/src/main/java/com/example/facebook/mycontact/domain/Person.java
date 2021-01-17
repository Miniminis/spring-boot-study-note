package com.example.facebook.mycontact.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor        //+@NonNull : 꼭 필요한 field 만 생성자로 만들 수 있음!
@Data                           //equalsAndHash 도 같이 설정되어잇음 : 내부에 들어가서 이미 세팅되어있는 요소들 확인 필요!
@ToString(exclude = {"phoneNumber", "job"})     //ToString 요소에서 제외해줄 필드 설정
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Integer age;

    private String bloodType;

    private String address;

    private LocalDate birthDay;

    private String job;

    private String phoneNumber;

    @OneToOne
    private Block block;

}
