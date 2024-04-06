package org.feup.coffeeshop.model.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.feup.coffeeshop.base.BaseServiceTest;
import org.feup.coffeeshop.model.dto.CoffeeShopDto;
import org.feup.coffeeshop.model.entity.OrderRequestEntity;

import static org.junit.jupiter.api.Assertions.assertNull;

class CoffeeShopConverterTest extends BaseServiceTest {

    private CoffeeShopConverter coffeeShopConverter;

    @BeforeEach
    void setUp() {
        coffeeShopConverter = new CoffeeShopConverter();
    }

    @Test
    void toDto_null() {
        final CoffeeShopDto coffeeShopDto = coffeeShopConverter.toDto(null);
        assertNull(coffeeShopDto);
    }

    @Test
    void toEntity_null() {
        final OrderRequestEntity orderRequestEntity = coffeeShopConverter.toEntity(null);
        assertNull(orderRequestEntity);
    }

}