package com.epam.training.webshop.core.coupon.converter;

import com.epam.training.webshop.core.coupon.dto.CouponDTO;
import com.epam.training.webshop.core.coupon.persistence.entity.Coupon;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CouponDTOToEntityConverter implements Function<CouponDTO, Coupon> {

    @Override
    public Coupon apply(final CouponDTO couponDTO) {
        return Coupon.builder()
                .id(couponDTO.getId())
                .name(couponDTO.getName())
                .couponValue(couponDTO.getValue())
                .build();
    }
}
