package io.github.antraxo.order_service.order.domain;

import io.github.antraxo.order_service.order.domain.model.Order;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository {

    Order save(Order newOrder);

    Optional<Order> findById(long orderId);

}
