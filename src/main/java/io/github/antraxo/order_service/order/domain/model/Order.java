package io.github.antraxo.order_service.order.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "orders")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerId;
    private LocalDateTime createdAt;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductOrder> productOrders;
    private BigDecimal totalAmount;
    private OrderStatus status;
}
