package com.epam.training.webshop.core.coupon.persistence.entity;

import com.epam.training.webshop.core.product.persistence.entitiy.Product;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Coupon {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    /*     A `value` foglalt szó a H2 esetében. */
    private double couponValue;

    @ManyToMany(mappedBy = "availableCoupons", fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Product> availableProducts = new ArrayList<>();

    public Coupon(final String name, final double couponValue, final List<Product> availableProducts) {
        this.name = name;
        this.couponValue = couponValue;
        this.availableProducts = availableProducts;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final Coupon coupon = (Coupon) o;

        return new EqualsBuilder().append(couponValue, coupon.couponValue).append(id, coupon.id).append(name, coupon.name).append(availableProducts, coupon.availableProducts).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(couponValue).append(availableProducts).toHashCode();
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", couponValue=" + couponValue +
//                ", availableProducts=" + availableProducts.stream().map(Product::toString).collect(Collectors.toList()) +
                '}';
    }
}
