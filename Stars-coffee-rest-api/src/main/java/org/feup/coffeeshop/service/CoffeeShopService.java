package org.feup.coffeeshop.service;

import org.feup.coffeeshop.model.dto.PurchaseDto;
import org.feup.coffeeshop.model.dto.UserDto;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.request.PurchaseRequest;
import org.feup.coffeeshop.model.response.AvailableItemsListResponse;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;
import org.feup.coffeeshop.model.response.PurchaseListResponse;
import org.feup.coffeeshop.model.response.VoucherListResponse;

public interface CoffeeShopService {
    OrderListResponse getCustomer(Long id);

    OrderListResponse getAllCustomers();

    VoucherListResponse getAllVouchers();

    UserDto createCustomer(OrderRequest request);

    UserDto login(OrderRequest request);

    UserDto updateCustomer(Long id, OrderRequest request);

    OrderDeleteResponse deleteCustomer(Long id);

    OrderDeleteResponse deleteAllCustomers();

    AvailableItemsListResponse getAvailableItems();

    PurchaseDto createPurchase(PurchaseRequest request);

}
