package org.feup.coffeeshop.service;

import org.feup.coffeeshop.base.BaseServiceTest;
import org.feup.coffeeshop.model.converter.CoffeeShopConverter;
import org.feup.coffeeshop.model.dto.CoffeeShopDto;
import org.feup.coffeeshop.model.entity.OrderRequestEntity;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;
import org.feup.coffeeshop.repository.CoffeeRepository;
import org.feup.coffeeshop.service.impl.CoffeeShopServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.feup.coffeeshop.builder.CustomerMockDataBuilder.generateCustomerRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


class CoffeeShopServiceTest extends BaseServiceTest {

    private static final long CUSTOMER_ID = 1;
    private OrderListResponse orderListResponse;
    private OrderRequest orderRequest;
    private OrderRequestEntity orderRequestEntity;
    private CoffeeShopDto coffeeShopDto;

    @MockBean
    private CoffeeRepository repository;

    private CoffeeShopService coffeeShopService;


    @BeforeEach
    void setUp() {
        final CoffeeShopConverter coffeeShopConverter = new CoffeeShopConverter();
        orderRequest = generateCustomerRequest();
        orderRequestEntity = coffeeShopConverter.toEntity(orderRequest);
        orderRequestEntity.setId(CUSTOMER_ID);
        coffeeShopDto = coffeeShopConverter.toDto(orderRequestEntity);
        orderListResponse = OrderListResponse.builder().customers(Collections.singletonList(coffeeShopDto)).build();
        coffeeShopService = new CoffeeShopServiceImpl(repository, coffeeShopConverter);
    }

    @Test
    void getCustomer_success() {
        when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.of(orderRequestEntity));
        final OrderListResponse response = coffeeShopService.getCustomer(CUSTOMER_ID);
        assertEquals(orderListResponse, response);
    }

    @Test
    void getAllCustomers_success() {
        when(repository.findAll()).thenReturn(Collections.singletonList(orderRequestEntity));
        final OrderListResponse response = coffeeShopService.getAllCustomers();
        assertEquals(orderListResponse, response);
    }

    @Test
    void getAllCustomers_notFound() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        final OrderListResponse response = coffeeShopService.getAllCustomers();
        assertTrue(CollectionUtils.isEmpty(response.getCustomers()));
    }

    @Test
    void createCustomer_success() {
        when(repository.save(any())).thenReturn(orderRequestEntity);
        final CoffeeShopDto response = coffeeShopService.createCustomer(orderRequest);
        assertEquals(coffeeShopDto, response);
    }

    @Test
    void updateCustomer_success() {
        when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.of(orderRequestEntity));
        when(repository.save(any())).thenReturn(orderRequestEntity);
        final CoffeeShopDto response = coffeeShopService.updateCustomer(CUSTOMER_ID, orderRequest);
        assertEquals(coffeeShopDto, response);
    }

    @Test
    void updateCustomer_notFound() {
        when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.empty());
        final CoffeeShopDto response = coffeeShopService.updateCustomer(CUSTOMER_ID, orderRequest);
        assertNull(response);
    }

    @Test
    void deleteCustomer_success() {
        when(repository.existsById(CUSTOMER_ID)).thenReturn(true);
        doNothing().when(repository).deleteById(CUSTOMER_ID);
        final OrderDeleteResponse response = coffeeShopService.deleteCustomer(CUSTOMER_ID);
        assertNotNull(response);
        assertEquals(1, response.getDeletedCustomerCount());
    }

    @Test
    void deleteCustomer_noContent() {
        when(repository.existsById(CUSTOMER_ID)).thenReturn(false);
        final OrderDeleteResponse response = coffeeShopService.deleteCustomer(CUSTOMER_ID);
        assertEquals(0, response.getDeletedCustomerCount());
    }

    @Test
    void deleteAllCustomers_success() {
        when(repository.count()).thenReturn((long) 1);
        doNothing().when(repository).deleteAll();
        final OrderDeleteResponse response = coffeeShopService.deleteAllCustomers();
        assertNotNull(response);
        assertEquals(1, response.getDeletedCustomerCount());
    }

    @Test
    void deleteAllCustomers_noContent() {
        when(repository.count()).thenReturn((long) 0);
        final OrderDeleteResponse response = coffeeShopService.deleteAllCustomers();
        assertEquals(0, response.getDeletedCustomerCount());
    }

}
