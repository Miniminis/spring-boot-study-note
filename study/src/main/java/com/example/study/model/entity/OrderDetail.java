package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"orderGroup", "item"})
@Builder
@Accessors(chain = true)
public class OrderDetail extends BaseEntity{

    private String status;

    private LocalDateTime arrivalDate;

    private int quantity;

    private BigDecimal totalPrice;

    //OrderDetail N : 1 orderGroup
    @ManyToOne
    private OrderGroup orderGroup;

    //orderDetail N : 1 Item
    @ManyToOne
    private Item item;
}
