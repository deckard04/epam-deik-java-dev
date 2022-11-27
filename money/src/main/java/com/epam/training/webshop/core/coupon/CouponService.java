package com.epam.training.webshop.core.coupon;

import com.epam.training.webshop.core.coupon.dto.CouponDTO;

import java.util.List;

public interface CouponService {

    CouponDTO createCoupon(String name, Double value, List<String> relatedProductsName);

    void deleteCoupon(Long id);

    CouponDTO findByName(String name);
}
