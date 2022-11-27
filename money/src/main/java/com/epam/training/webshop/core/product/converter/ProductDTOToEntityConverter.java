package com.epam.training.webshop.core.product.converter;

import com.epam.training.webshop.core.coupon.converter.CouponDTOToEntityConverter;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import com.epam.training.webshop.core.product.persistence.entitiy.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductDTOToEntityConverter implements Function<ProductDTO, Product> {

    private final CouponDTOToEntityConverter converter;

    @Override
    public Product apply(final ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .packaging(productDTO.getPackaging())
                .netPrice(productDTO.getNetPrice().getAmount())
                .currencyCode(productDTO.getNetPrice().getCurrencyCode())
                .build();
    }
}
