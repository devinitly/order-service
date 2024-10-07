package io.github.antraxo.order_service.order.domain;

import io.github.antraxo.order_service.order.domain.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderRepositoryTestImpl implements OrderRepository {
    Map<Long, Order> db = new HashMap<>();

    @Override
    public Order save(Order newOrder) {
        newOrder.setId((long) (db.size() + 1));
        db.put(newOrder.getId(), newOrder);
        return db.get(newOrder.getId());
    }

    @Override
    public Optional<Order> findById(long orderId) {
        return Optional.ofNullable(db.get(orderId));
    }
}
