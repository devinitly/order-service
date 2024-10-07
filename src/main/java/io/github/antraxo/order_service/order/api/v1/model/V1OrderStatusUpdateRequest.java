package io.github.antraxo.order_service.order.api.v1.model;

import io.github.antraxo.order_service.order.domain.model.OrderStatus;
import lombok.Data;

@Data
public class V1OrderStatusUpdateRequest {
    private OrderStatus status;
}
