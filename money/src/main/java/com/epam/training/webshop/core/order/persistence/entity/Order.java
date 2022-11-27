package com.epam.training.webshop.core.order.persistence.entity;

import com.epam.training.webshop.core.user.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;
    private Double netPriceAmount;
    private String netPriceCurrencyCode;
    private Double grossPriceAmount;
    private String grossPriceCurrencyCode;
}
