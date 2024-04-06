package org.feup.coffeeshop.controller;

import org.feup.coffeeshop.model.dto.CoffeeShopDto;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;
import org.feup.coffeeshop.service.CoffeeShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoffeeShopController {

    private final CoffeeShopService coffeeShopService;

    public CoffeeShopController(CoffeeShopService coffeeShopService) {
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

    @GetMapping("/get-available-items")
    public ResponseEntity<OrderListResponse> getAvailableItems() {
        OrderListResponse response = coffeeShopService.getAllCustomers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-vouchers")
    public ResponseEntity<OrderListResponse> getVouchers() {
        OrderListResponse response = coffeeShopService.getAllCustomers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<CoffeeShopDto> createCustomer(@RequestBody OrderRequest request) {
        return new ResponseEntity<>(coffeeShopService.createCustomer(request), HttpStatus.CREATED);
    }

    @PostMapping("/place-order")
    public ResponseEntity<CoffeeShopDto> placeOrder(@RequestBody OrderRequest request) {
        return new ResponseEntity<>(coffeeShopService.createCustomer(request), HttpStatus.CREATED);
    }

    @PostMapping("/validate-order")
    public ResponseEntity<CoffeeShopDto> validateOrder(@RequestBody OrderRequest request) {
        return new ResponseEntity<>(coffeeShopService.createCustomer(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<CoffeeShopDto> login(@RequestBody OrderRequest request) {
        return new ResponseEntity<>(coffeeShopService.login(request), HttpStatus.CREATED);
    }
    @PutMapping("/update-customer/{id}")
    public ResponseEntity<CoffeeShopDto> updateCustomer(@PathVariable("id") Long id, @RequestBody OrderRequest request) {
        CoffeeShopDto coffeeShopDto = coffeeShopService.updateCustomer(id, request);
        if (null == coffeeShopDto) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(coffeeShopDto, HttpStatus.OK);
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
