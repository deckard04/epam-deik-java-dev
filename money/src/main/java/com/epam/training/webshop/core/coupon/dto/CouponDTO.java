package com.epam.training.webshop.core.coupon.dto;

import com.epam.training.webshop.core.product.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
@RequiredArgsConstructor
public class CouponDTO {

    private final Long id;
    private final String name;
    private final double value;
    private final List<ProductDTO> relatedProducts;
}
