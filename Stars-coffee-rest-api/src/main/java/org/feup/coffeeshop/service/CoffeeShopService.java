package org.feup.coffeeshop.service;

import org.feup.coffeeshop.model.dto.PurchaseDto;
import org.feup.coffeeshop.model.dto.UserDto;
import org.feup.coffeeshop.model.dto.SummaryDto;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.request.PurchaseRequest;
import org.feup.coffeeshop.model.response.FoodsListResponse;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;
import org.feup.coffeeshop.model.response.PurchaseListResponse;
import org.feup.coffeeshop.model.response.VoucherListResponse;
import org.feup.coffeeshop.model.request.VoucherRequest;
import org.feup.coffeeshop.model.request.SummaryRequest;

public interface CoffeeShopService {
    OrderListResponse getCustomer(Long id);

    OrderListResponse getCustomerByEmail(String email);
    OrderListResponse getAllCustomers();

    VoucherListResponse getAllVouchers(String userId);

    VoucherListResponse getVoucher(Long id);

    VoucherListResponse addVoucher(VoucherRequest request);

    UserDto createCustomer(OrderRequest request);

    UserDto login(OrderRequest request);

    UserDto updateCustomer(Long id, OrderRequest request);

    OrderDeleteResponse deleteCustomer(Long id);

    OrderDeleteResponse deleteAllCustomers();

    FoodsListResponse getFoods();

    PurchaseDto createPurchase(PurchaseRequest request);

    OrderListResponse validateOrder(SummaryRequest request);
}
