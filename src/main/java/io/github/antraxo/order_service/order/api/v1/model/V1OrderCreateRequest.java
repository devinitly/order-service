package io.github.antraxo.order_service.order.api.v1.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class V1OrderCreateRequest {
    @NotBlank
    private String customerId;
    @NotNull
    @Valid
    private Set<V1ProductOrder> productOrders;
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal totalAmount;
}
