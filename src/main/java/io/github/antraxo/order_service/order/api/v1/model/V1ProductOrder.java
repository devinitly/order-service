package io.github.antraxo.order_service.order.api.v1.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class V1ProductOrder {
    @Positive
    private int quantity;
    @NotBlank
    private String productId;
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal productPrice;
}
