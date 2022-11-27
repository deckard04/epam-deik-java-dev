package com.epam.training.webshop.core.coupon.converter;

import com.epam.training.webshop.core.coupon.dto.CouponDTO;
import com.epam.training.webshop.core.coupon.persistence.entity.Coupon;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CouponEntityToDTOConverter implements Function<Coupon, CouponDTO> {

    @Override
    public CouponDTO apply(final Coupon coupon) {
        return CouponDTO.builder()
                .id(coupon.getId())
                .name(coupon.getName())
                .value(coupon.getCouponValue())
                .build();
    }
}
