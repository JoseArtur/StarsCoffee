package org.feup.coffeeshop.service.impl;

import org.feup.coffeeshop.repository.CoffeeRepository;
import org.feup.coffeeshop.model.converter.CoffeeShopConverter;
import org.feup.coffeeshop.model.dto.CoffeeShopDto;
import org.feup.coffeeshop.model.entity.OrderRequestEntity;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;
import org.feup.coffeeshop.service.CoffeeShopService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeeShopServiceImpl implements CoffeeShopService {

    private final CoffeeRepository repository;
    private final CoffeeShopConverter coffeeShopConverter;

    public CoffeeShopServiceImpl(CoffeeRepository repository, CoffeeShopConverter coffeeShopConverter) {
        this.repository = repository;
        this.coffeeShopConverter = coffeeShopConverter;
    }


    @Override
    public OrderListResponse getCustomer(Long id) {
        final OrderListResponse response = new OrderListResponse();
        return repository.findById(id)
                .map(entity -> OrderListResponse.builder().customers(Collections.singletonList(coffeeShopConverter.toDto(entity))).build())
                .orElse(response);
    }

    @Override
    public OrderListResponse getAllCustomers() {
        final List<OrderRequestEntity> entities = repository.findAll();

        final List<CoffeeShopDto> converted = entities
                .stream()
                .map(coffeeShopConverter::toDto)
                .collect(Collectors.toList());

        return OrderListResponse.builder().customers(converted).build();

    }

    @Override
    public CoffeeShopDto createCustomer(OrderRequest request) {
        final OrderRequestEntity saved = repository.save(coffeeShopConverter.toEntity(request));
        return coffeeShopConverter.toDto(saved);
    }

    @Override
    public CoffeeShopDto login(OrderRequest request) {
        //final OrderRequestEntity response = repository.findByEmail(request.getEmail(), request.getPassword());
        return null; //coffeeShopConverter.toDto(response);
    }

    @Override
    public CoffeeShopDto updateCustomer(Long id, OrderRequest request) {
        final Optional<OrderRequestEntity> optionalCustomerEntity = repository.findById(id);
        if (!optionalCustomerEntity.isPresent()) {
            return null;
        } else {
            final OrderRequestEntity toBeUpdated = coffeeShopConverter.toEntity(request);
            toBeUpdated.setId(optionalCustomerEntity.get().getId());
            final OrderRequestEntity saved = repository.save(toBeUpdated);
            return coffeeShopConverter.toDto(saved);
        }

    }

    @Override
    public OrderDeleteResponse deleteCustomer(Long id) {
        if (!repository.existsById(id)) {
            return OrderDeleteResponse.builder().deletedCustomerCount(0L).build();
        } else {
            repository.deleteById(id);
            return OrderDeleteResponse.builder().deletedCustomerCount(1L).build();
        }
    }

    @Override
    public OrderDeleteResponse deleteAllCustomers() {
        final long count = repository.count();
        if (count == 0) {
            return OrderDeleteResponse.builder().deletedCustomerCount(0L).build();
        } else {
            repository.deleteAll();
            return OrderDeleteResponse.builder().deletedCustomerCount(count).build();
        }
    }

}

