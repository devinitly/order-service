package io.github.antraxo.order_service.order.api.v1.transform;

import io.github.antraxo.order_service.order.api.v1.model.V1OrderCreateRequest;
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
public interface OrderRequestMapper {
    @Mapping(target = "customerId", source = "orderCreateRequest.customerId")
    @Mapping(target = "productOrders", source = "orderCreateRequest.productOrders", qualifiedByName = "mapProductOrderSet")
    @Mapping(target = "totalAmount", source = "orderCreateRequest.totalAmount")
    Order map(V1OrderCreateRequest orderCreateRequest);

    @Named("mapProductOrderSet")
    Set<ProductOrder> mapProductOrderSet(Set<V1ProductOrder> productOrders);

    @Mapping(target = "quantity", source = "productOrderRequest.quantity")
    @Mapping(target = "productId", source = "productOrderRequest.productId")
    @Mapping(target = "productPrice", source = "productOrderRequest.productPrice")
    ProductOrder mapProductOrder(V1ProductOrder productOrderRequest);
}
