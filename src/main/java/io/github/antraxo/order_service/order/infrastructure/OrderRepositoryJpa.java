package io.github.antraxo.order_service.order.infrastructure;

import io.github.antraxo.order_service.order.domain.OrderRepository;
import io.github.antraxo.order_service.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepositoryJpa extends OrderRepository, JpaRepository<Order, Long> {

}
