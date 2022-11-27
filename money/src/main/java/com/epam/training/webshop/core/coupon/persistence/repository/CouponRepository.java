package com.epam.training.webshop.core.coupon.persistence.repository;

import com.epam.training.webshop.core.coupon.persistence.entity.Coupon;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

    Optional<Coupon> findByName(String name);
}
