package org.feup.coffeeshop.model.converter;

import org.feup.coffeeshop.model.dto.CoffeeShopDto;
import org.feup.coffeeshop.model.entity.OrderRequestEntity;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.springframework.stereotype.Component;

@Component
public class CoffeeShopConverter {
    public CoffeeShopDto toDto(OrderRequestEntity entity) {
        if (entity == null) {
            return null;
        } else {
            return CoffeeShopDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .surname(entity.getSurname())
                    .telephone(entity.getTelephone())
                    .password(entity.getPassword())
                    .email(entity.getEmail())
                    .build();
        }
    }

    public OrderRequestEntity toEntity(OrderRequest request) {
        if (request == null) {
            return null;
        } else {
            return OrderRequestEntity.builder()
                    .name(request.getName())
                    .surname(request.getSurname())
                    .telephone(request.getTelephone())
                    .password(request.getPassword())
                    .email(request.getEmail())
                    .build();
        }
    }

}
