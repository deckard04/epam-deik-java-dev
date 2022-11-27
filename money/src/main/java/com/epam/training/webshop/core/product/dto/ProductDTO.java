package com.epam.training.webshop.core.product.dto;

import com.epam.training.webshop.core.coupon.dto.CouponDTO;
import com.epam.training.webshop.core.financial.model.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;


@RequiredArgsConstructor
@Builder
@Getter
public class ProductDTO {

    private final Long id;
    private final String name;
    private final Money netPrice;
    private final String packaging;
    private final List<CouponDTO> availableCoupons;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", netPrice=" + netPrice.getAmount() + " " + netPrice.getCurrencyCode() + '\'' +
                ", packaging='" + packaging + '\'' +
                ", availableCoupons=" + availableCoupons +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ProductDTO that = (ProductDTO) o;

        return new EqualsBuilder().append(id, that.id).append(name, that.name).append(netPrice, that.netPrice).append(packaging, that.packaging).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(netPrice).append(packaging).toHashCode();
    }
}
