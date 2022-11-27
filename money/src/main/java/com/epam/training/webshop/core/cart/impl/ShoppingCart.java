package com.epam.training.webshop.core.cart.impl;

import com.epam.training.webshop.core.cart.Cart;
import com.epam.training.webshop.core.coupon.dto.CouponDTO;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Getter
@EqualsAndHashCode
@ToString
public class ShoppingCart implements Cart {

    private final List<ProductDTO> products;
    private final List<CouponDTO> coupons;

    public ShoppingCart() {
        this.products = new ArrayList<>();
        this.coupons = new ArrayList<>();
    }

    @Override
    public List<ProductDTO> getProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public void addProduct(final ProductDTO product) {
        this.products.add(product);
    }

    @Override
    public void removeProduct(final ProductDTO productToRemove) {
        products.remove(productToRemove);
    }

    @Override
    public void addCoupon(final CouponDTO coupon) {
        coupons.add(coupon);
    }

    @Override
    public List<CouponDTO> getCouponsFromBasket() {
        return coupons;
    }

    @Override
    public void removeAllProducts() {
        products.clear();
    }
}
