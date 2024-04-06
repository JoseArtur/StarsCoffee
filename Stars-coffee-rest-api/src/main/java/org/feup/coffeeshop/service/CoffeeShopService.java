package org.feup.coffeeshop.service;

import org.feup.coffeeshop.model.dto.CoffeeShopDto;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;

public interface CoffeeShopService {
    OrderListResponse getCustomer(Long id);

    OrderListResponse getAllCustomers();

    CoffeeShopDto createCustomer(OrderRequest request);

    CoffeeShopDto login(OrderRequest request);

    CoffeeShopDto updateCustomer(Long id, OrderRequest request);

    OrderDeleteResponse deleteCustomer(Long id);

    OrderDeleteResponse deleteAllCustomers();

}
