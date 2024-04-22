package org.feup.coffeeshop.controller;

import org.feup.coffeeshop.model.dto.PurchaseDto;
import org.feup.coffeeshop.model.dto.UserDto;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.request.PurchaseRequest;
import org.feup.coffeeshop.model.response.FoodsListResponse;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;
import org.feup.coffeeshop.service.CoffeeShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class StarsCoffeeController {

    private final CoffeeShopService coffeeShopService;

    public StarsCoffeeController(CoffeeShopService coffeeShopService) {
        this.coffeeShopService = coffeeShopService;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<OrderListResponse> getCustomer(@PathVariable("id") Long id) {
        OrderListResponse response = coffeeShopService.getCustomer(id);

        if (CollectionUtils.isEmpty(response.getCustomers())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @GetMapping("/get-all-customers")
    public ResponseEntity<OrderListResponse> getAllCustomers() {
        OrderListResponse response = coffeeShopService.getAllCustomers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all-foods")
    public ResponseEntity<FoodsListResponse> getFoods() {
        FoodsListResponse response = coffeeShopService.getFoods();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all-vouchers")
    public ResponseEntity<VoucherListResponse> getAllVouchers() {
        VoucherListResponse response = coffeeShopService.getAllVouchers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createCustomer(@RequestBody OrderRequest request) {
        return new ResponseEntity<>(coffeeShopService.createCustomer(request), HttpStatus.CREATED);
    }

    @PostMapping("/place-order")
    public ResponseEntity<PurchaseDto> placeOrder(@RequestBody PurchaseRequest request) {
        return new ResponseEntity<>(coffeeShopService.createPurchase(request), HttpStatus.CREATED);
    }

    @PostMapping("/validate-order")
    public ResponseEntity<UserDto> validateOrder(@RequestBody OrderRequest request) {
        return new ResponseEntity<>(coffeeShopService.createCustomer(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody OrderRequest request) {
        return new ResponseEntity<>(coffeeShopService.login(request), HttpStatus.CREATED);
    }
    @PutMapping("/update-customer/{id}")
    public ResponseEntity<UserDto> updateCustomer(@PathVariable("id") Long id, @RequestBody OrderRequest request) {
        UserDto userDto = coffeeShopService.updateCustomer(id, request);
        if (null == userDto) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<OrderDeleteResponse> deleteCustomer(@PathVariable("id") Long id) {
        OrderDeleteResponse response = coffeeShopService.deleteCustomer(id);
        if (0L == response.getDeletedCustomerCount()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete-all-customers")
    public ResponseEntity<OrderDeleteResponse> deleteAllCustomers() {
        OrderDeleteResponse response = coffeeShopService.deleteAllCustomers();
        if (0L == response.getDeletedCustomerCount()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
