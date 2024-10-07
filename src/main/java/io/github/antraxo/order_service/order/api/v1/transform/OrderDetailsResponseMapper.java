package io.github.antraxo.order_service.order.api.v1.transform;

import io.github.antraxo.order_service.order.api.v1.model.V1OrderDetailsResponse;
import io.github.antraxo.order_service.order.api.v1.model.V1ProductOrder;
import io.github.antraxo.order_service.order.domain.model.Order;
import io.github.antraxo.order_service.order.domain.model.ProductOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper
@Component
public interface OrderDetailsResponseMapper {
    @Mapping(target = "customerId", source = "order.customerId")
    @Mapping(target = "createdAt", source = "order.createdAt")
    @Mapping(target = "productOrders", source = "order.productOrders", qualifiedByName = "mapProductOrderSetResponse")
    @Mapping(target = "totalAmount", source = "order.totalAmount")
    @Mapping(target = "status", source = "order.status")
    V1OrderDetailsResponse map(Order order);

    @Named("mapProductOrderSetResponse")
    Set<V1ProductOrder> mapProductOrderSetResponse(Set<ProductOrder> productOrders);

    @Mapping(target = "quantity", source = "productOrder.quantity")
    @Mapping(target = "productId", source = "productOrder.productId")
    @Mapping(target = "productPrice", source = "productOrder.productPrice")
    V1ProductOrder mapProductOrder(ProductOrder productOrder);
}
