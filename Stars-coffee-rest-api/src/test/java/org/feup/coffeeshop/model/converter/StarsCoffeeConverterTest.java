package org.feup.coffeeshop.model.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.feup.coffeeshop.base.BaseServiceTest;
import org.feup.coffeeshop.model.dto.UserDto;
import org.feup.coffeeshop.model.entity.OrderRequestEntity;

import static org.junit.jupiter.api.Assertions.assertNull;

class StarsCoffeeConverterTest extends BaseServiceTest {

    private StarsCoffeeConverter starsCoffeeConverter;

    @BeforeEach
    void setUp() {
        starsCoffeeConverter = new StarsCoffeeConverter();
    }

    @Test
    void toDto_null() {
        final UserDto userDto = starsCoffeeConverter.toDto(null);
        assertNull(userDto);
    }

    @Test
    void toEntity_null() {
        final OrderRequestEntity orderRequestEntity = starsCoffeeConverter.toEntity(null);
        assertNull(orderRequestEntity);
    }

}