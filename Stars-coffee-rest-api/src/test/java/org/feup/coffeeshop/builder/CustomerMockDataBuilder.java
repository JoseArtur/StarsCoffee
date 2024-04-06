package org.feup.coffeeshop.builder;

import org.feup.coffeeshop.model.dto.CoffeeShopDto;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;

import java.util.Collections;

public class CustomerMockDataBuilder {

    private CustomerMockDataBuilder() {
    }

    public static OrderListResponse generateCustomerListResponse(long customerId) {
        return OrderListResponse.builder()
                .customers(Collections.singletonList(generateCustomerDto(customerId)))
                .build();
    }

    public static OrderRequest generateCustomerRequest() {
        return GenericMockDataBuilder.of(OrderRequest.class).build();
    }

    public static OrderDeleteResponse generateCustomerDeleteResponse() {
        return OrderDeleteResponse.builder()
                .deletedCustomerCount(1L)
                .build();
    }

    public static CoffeeShopDto generateCustomerDto(long customerId) {
        final CoffeeShopDto dto = GenericMockDataBuilder.of(CoffeeShopDto.class)
                .excludeField("id")
                .build();
        dto.setId(customerId);
        return dto;
    }

}
