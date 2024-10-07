package io.github.antraxo.order_service.order.domain;


import io.github.antraxo.order_service.date.DateProvider;
import io.github.antraxo.order_service.order.domain.exception.StatusUpdateNotAllowedException;
import io.github.antraxo.order_service.order.domain.model.Order;
import io.github.antraxo.order_service.order.domain.model.OrderStatus;
import io.github.antraxo.order_service.order.domain.model.ProductOrder;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    DateProvider dateProvider = mock(DateProvider.class);
    OrderRepositoryTestImpl repository = new OrderRepositoryTestImpl();
    OrderService testing = new OrderService(repository, dateProvider);

    @BeforeEach
    void setUp() {
        when(dateProvider.getCurrentDateTime()).thenReturn(LocalDateTime.MAX);
        repository.db = new HashMap<>();
    }

    @Test
    void shouldSaveNewOrder() {
        //given
        var testOrder = buildOrder();

        //when
        var result = testing.createNewOrder(testOrder);

        //then
        Assertions.assertEquals(1L, result);
    }

    @Test
    void shouldSaveAndUpdateStatusToConfirmed() throws StatusUpdateNotAllowedException {
        //given
        var testOrder = buildOrder();
        var orderId = testing.createNewOrder(testOrder);

        //when
        testing.updateOrderStatus(orderId, OrderStatus.CONFIRMED);

        //then
        Assertions.assertEquals(OrderStatus.CONFIRMED, testing.getOrderDetails(orderId).getStatus());
    }

    @Test
    void shouldSaveAndNotAllowToUpdateWhenStatusCreatedUpdatedToFinished() {
        //given
        var testOrder = buildOrder();
        var orderId = testing.createNewOrder(testOrder);

        //when then
        Assertions.assertThrows(StatusUpdateNotAllowedException.class, () -> testing.updateOrderStatus(orderId, OrderStatus.FINISHED));
    }

    @Test
    void shouldSaveAndNotAllowToUpdateWhenTerminatingStatusIsUpdated() throws StatusUpdateNotAllowedException {
        //given
        var testOrder = buildOrder();
        var orderId = testing.createNewOrder(testOrder);
        testing.updateOrderStatus(orderId, OrderStatus.CANCELLED);

        //when then
        Assertions.assertThrows(StatusUpdateNotAllowedException.class, () -> testing.updateOrderStatus(orderId, OrderStatus.CONFIRMED));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUpdatingNotExistingOrder() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> testing.updateOrderStatus(1L, OrderStatus.CONFIRMED));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenQueryingNotExistingOrder() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> testing.getOrderDetails(1L));
    }

    @Test
    void shouldReturnOrderWithDateAndStatusAfterSave() {
        //given
        var testOrder = buildOrder();

        //when
        var orderId = testing.createNewOrder(testOrder);
        var result = testing.getOrderDetails(orderId);

        //then
        Assertions.assertEquals(LocalDateTime.MAX, result.getCreatedAt());
        Assertions.assertEquals(OrderStatus.CREATED, result.getStatus());
    }


    Order buildOrder() {
        ProductOrder productOrder = ProductOrder.builder()
                .productId("1")
                .productPrice(new BigDecimal(25))
                .quantity(2)
                .build();
        Set<ProductOrder> set = new HashSet<>();
        set.add(productOrder);
        return Order.builder()
                .productOrders(set)
                .customerId("1")
                .totalAmount(new BigDecimal(50))
                .build();
    }
}
