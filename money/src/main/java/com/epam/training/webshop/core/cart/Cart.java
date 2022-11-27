package com.epam.training.webshop.core.cart;

import com.epam.training.webshop.core.coupon.dto.CouponDTO;
import com.epam.training.webshop.core.product.dto.ProductDTO;

import java.util.List;

public interface Cart {

    List<ProductDTO> getProducts();

    void addProduct(ProductDTO product);

    void removeProduct(ProductDTO productToRemove);

    void addCoupon(CouponDTO coupon);

    List<CouponDTO> getCouponsFromBasket();

    void removeAllProducts();

}
