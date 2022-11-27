package com.epam.training.webshop.core.order.persistence.repository;

import com.epam.training.webshop.core.order.persistence.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserUsername(String userName);
}
