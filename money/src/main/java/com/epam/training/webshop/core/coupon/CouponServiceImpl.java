package com.epam.training.webshop.core.coupon;

import com.epam.training.webshop.core.coupon.converter.CouponEntityToDTOConverter;
import com.epam.training.webshop.core.coupon.dto.CouponDTO;
import com.epam.training.webshop.core.coupon.persistence.entity.Coupon;
import com.epam.training.webshop.core.coupon.persistence.repository.CouponRepository;
import com.epam.training.webshop.core.product.persistence.entitiy.Product;
import com.epam.training.webshop.core.product.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final ProductRepository productRepository;
    private final CouponEntityToDTOConverter converter;

    @Override
    public CouponDTO createCoupon(final String name, final Double value, final List<String> relatedProductsName) {
        return converter.apply(
                couponRepository.save(
                        Coupon.builder()
                                .name(name)
                                .couponValue(value)
                                .availableProducts(findProducts(relatedProductsName))
                                .build()
                )
        );
    }

    @Override
    public void deleteCoupon(final Long id) {
        couponRepository.deleteById(id);
    }

    @Override
    public CouponDTO findByName(final String name) {
        return couponRepository.findByName(name)
                .map(converter::apply)
                .orElse(null);
    }

    private List<Product> findProducts(final List<String> productNames) {
        return productNames.stream()
                .map(productRepository::findProductByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
