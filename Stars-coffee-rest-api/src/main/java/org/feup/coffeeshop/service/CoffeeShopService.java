package org.feup.coffeeshop.service;

import org.feup.coffeeshop.model.dto.PurchaseDto;
import org.feup.coffeeshop.model.dto.UserDto;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.request.PurchaseRequest;
import org.feup.coffeeshop.model.response.FoodsListResponse;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;
import org.feup.coffeeshop.model.response.PurchaseListResponse;

public interface CoffeeShopService {
    OrderListResponse getCustomer(Long id);

    OrderListResponse getAllCustomers();

    UserDto createCustomer(OrderRequest request);

    UserDto login(OrderRequest request);

    UserDto updateCustomer(Long id, OrderRequest request);

    OrderDeleteResponse deleteCustomer(Long id);

    OrderDeleteResponse deleteAllCustomers();

    FoodsListResponse getFoods();

    PurchaseDto createPurchase(PurchaseRequest request);

}
