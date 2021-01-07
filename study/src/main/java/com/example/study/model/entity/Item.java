package com.example.study.model.entity;

import com.example.study.model.enumclass.ItemStatus;
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
@ToString(exclude = {"orderDetails", "partner"})
@Builder
@Accessors(chain = true)
public class Item extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    //item N : 1 partner
    @ManyToOne
    private Partner partner;

    //Item 1 : N orderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetails;
}
