package io.github.antraxo.order_service.order.domain;

import io.github.antraxo.order_service.date.DateProvider;
import io.github.antraxo.order_service.order.domain.exception.StatusUpdateNotAllowedException;
import io.github.antraxo.order_service.order.domain.model.Order;
import io.github.antraxo.order_service.order.domain.model.OrderStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DateProvider dateProvider;

    public long createNewOrder(Order newOrder) {
        newOrder.setCreatedAt(dateProvider.getCurrentDateTime());
        newOrder.setStatus(OrderStatus.CREATED);
        var order = orderRepository.save(newOrder);
        return order.getId();
    }

    public void updateOrderStatus(long orderId, OrderStatus status) throws StatusUpdateNotAllowedException {
        var order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        if(order.getStatus() == OrderStatus.FINISHED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new StatusUpdateNotAllowedException("Order has terminating status");
        }
        if(status == OrderStatus.FINISHED && order.getStatus() != OrderStatus.CONFIRMED) {
            throw new StatusUpdateNotAllowedException("Order is not confirmed, can't be finished");
        }
        order.setStatus(status);
        orderRepository.save(order);
    }

    public Order getOrderDetails(long orderId) throws EntityNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }
}
