package com.epam.training.webshop.core.product.converter;

import com.epam.training.webshop.core.coupon.converter.CouponEntityToDTOConverter;
import com.epam.training.webshop.core.coupon.persistence.entity.Coupon;
import com.epam.training.webshop.core.financial.model.Money;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import com.epam.training.webshop.core.product.persistence.entitiy.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductEntityToDTOConverter implements Function<Product, ProductDTO> {

    private final CouponEntityToDTOConverter converter;

    @Override
    public ProductDTO apply(final Product product) {
        final List<Coupon> coupons = product.getAvailableCoupons() == null ? List.of() : product.getAvailableCoupons();
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .netPrice(new Money(product.getNetPrice(), Currency.getInstance(product.getCurrencyCode())))
                .packaging(product.getPackaging())
                .availableCoupons(coupons.stream()
                        .map(converter)
                        .collect(Collectors.toList()))
                .build();
    }
}
