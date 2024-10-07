package io.github.antraxo.order_service.order.api.v1.model;

import io.github.antraxo.order_service.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class V1OrderDetailsResponse {
    private String customerId;
    private LocalDateTime createdAt;
    private Set<V1ProductOrder> productOrders;
    private BigDecimal totalAmount;
    private OrderStatus status;
}
