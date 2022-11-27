package com.epam.training.webshop.core.product.persistence.entitiy;

import com.epam.training.webshop.core.coupon.persistence.entity.Coupon;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private double netPrice;

    private String currencyCode;
    private String packaging;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "product_coupon",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id")
    )
    private List<Coupon> availableCoupons = new ArrayList<>();

    public Product(final String name, final double netPrice, final String currencyCode, final String packaging, final List<Coupon> availableCoupons) {
        this.name = name;
        this.netPrice = netPrice;
        this.packaging = packaging;
        this.availableCoupons = availableCoupons;
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Product product = (Product) o;

        return new EqualsBuilder().append(netPrice, product.netPrice).append(id, product.id).append(name, product.name).append(currencyCode, product.currencyCode).append(packaging, product.packaging).append(availableCoupons, product.availableCoupons).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(netPrice).append(currencyCode).append(packaging).append(availableCoupons).toHashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", netPrice=" + netPrice +
                ", currencyCode='" + currencyCode + '\'' +
                ", packaging='" + packaging + '\'' +
                ", availableCoupons=" + availableCoupons +
                '}';
    }
}
