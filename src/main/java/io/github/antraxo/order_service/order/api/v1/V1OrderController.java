package io.github.antraxo.order_service.order.api.v1;

import io.github.antraxo.order_service.order.api.v1.model.V1OrderCreateRequest;
import io.github.antraxo.order_service.order.api.v1.model.V1OrderDetailsResponse;
import io.github.antraxo.order_service.order.api.v1.model.V1OrderStatus;
import io.github.antraxo.order_service.order.api.v1.transform.OrderDetailsResponseMapper;
import io.github.antraxo.order_service.order.api.v1.transform.OrderRequestMapper;
import io.github.antraxo.order_service.order.domain.OrderService;
import io.github.antraxo.order_service.order.domain.exception.StatusUpdateNotAllowedException;
import io.github.antraxo.order_service.order.domain.model.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
@RestController
public class V1OrderController {
    private final OrderService orderService;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderDetailsResponseMapper orderDetailsResponseMapper;

    @Operation(description = "Create new order")
    @PostMapping
    ResponseEntity<Long> createNewOrder(@Valid @RequestBody V1OrderCreateRequest v1OrderCreateRequest) {
        var orderId = orderService.createNewOrder(orderRequestMapper.map(v1OrderCreateRequest));
        return ResponseEntity.ok(orderId);
    }

    @Operation(description = "Update order status")
    @PatchMapping(path = "/{orderId}")
    ResponseEntity<Void> updateOrderStatus(@PathVariable String orderId, @RequestBody V1OrderStatus orderStatus) {
        try {
            orderService.updateOrderStatus(Long.parseLong(orderId), OrderStatus.valueOf(orderStatus.name()));
        } catch (StatusUpdateNotAllowedException ex) {
            ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Get order details")
    @GetMapping(path = "/{orderId}")
    ResponseEntity<V1OrderDetailsResponse> getOrderDetails(@PathVariable String orderId) {
        try {
            var details = orderService.getOrderDetails(Long.parseLong(orderId));
            return ResponseEntity.ok(orderDetailsResponseMapper.map(details));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
