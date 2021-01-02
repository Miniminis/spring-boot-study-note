package com.example.study.model.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"user", "orderDetails"})
@Builder
@Accessors(chain = true)
public class OrderGroup extends BaseEntity {

    private String status;

    private String orderType;

    private String revAddress;

    private String revName;

    private String paymentType;

    private BigDecimal totalPrice;

    private int totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    // orderGroup N : 1 user
    @ManyToOne
    private User user;

    //orderGroup 1 : N orderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetails;
}
