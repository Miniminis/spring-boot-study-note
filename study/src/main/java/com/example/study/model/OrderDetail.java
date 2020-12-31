package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity     //- order_detail
@ToString(exclude = {"user", "item"})       //toString 제외 - User-OderDetail, Item-OrderDetail 상호참조 때문에 에러발생함
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    @ManyToOne
    private User user;      //user_id

    @ManyToOne
    private Item item;      //item_id

}
