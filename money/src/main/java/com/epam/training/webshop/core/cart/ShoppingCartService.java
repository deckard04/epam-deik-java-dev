package com.epam.training.webshop.core.cart;

import com.epam.training.webshop.core.coupon.dto.CouponDTO;
import com.epam.training.webshop.core.product.dto.ProductDTO;

import java.util.List;

public interface ShoppingCartService {

    List<ProductDTO> getProducts();

    void addProduct(ProductDTO product);

    void addProduct(String name);

    void removeProduct(ProductDTO productToRemove);

    void addCoupon(String couponName);

    List<CouponDTO> getCouponsFromBasket();

    double getTotalNetPrice();

    double getTotalGrossPrice();

    double getBasePrice();

    double getDiscountForCoupons();

    void clearCart();

    Cart getCart();
}

