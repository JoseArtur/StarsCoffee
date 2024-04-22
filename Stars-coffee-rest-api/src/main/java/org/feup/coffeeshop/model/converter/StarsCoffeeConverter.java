package org.feup.coffeeshop.model.converter;

import org.feup.coffeeshop.model.dto.FoodsDto;
import org.feup.coffeeshop.model.dto.PurchaseDto;
import org.feup.coffeeshop.model.dto.UserDto;
import org.feup.coffeeshop.model.dto.VoucherDto;
import org.feup.coffeeshop.model.entity.FoodsRequestEntity;
import org.feup.coffeeshop.model.entity.LoginRequestEntity;
import org.feup.coffeeshop.model.entity.OrderRequestEntity;
import org.feup.coffeeshop.model.entity.PurchaseRequestEntity;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.request.PurchaseRequest;
import org.springframework.stereotype.Component;

@Component
public class StarsCoffeeConverter {
    public UserDto toDto(OrderRequestEntity entity) {
        if (entity == null) {
            return null;
        } else {
            return UserDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .nif(entity.getNif())
                    .telephone(entity.getTelephone())
                    .password(entity.getPassword())
                    .email(entity.getEmail())
                    .build();
        }
    }

    public FoodsDto toFoodsDto(FoodsRequestEntity entity) {
        if (entity == null) {
            return null;
        } else {
            return FoodsDto.builder()
                    .id(entity.getId())
                    .foodName(entity.getFoodName())
                    .description(entity.getDescription())
                    .price(entity.getPrice())
                    .itemType(entity.getItemType())
                    .imageUrl(entity.getImageUrl())
                    .isFavorite(entity.getIsFavorite())
                    .isAvailable(entity.getIsAvailable())
                    .quantity(entity.getQuantity())
                    .customizationOptions(entity.getCustomizationOptions())
                    .specialAttributes(entity.getSpecialAttributes())
                    .build();
        }
    }

    public UserDto toLoginDto(LoginRequestEntity entity) {
        if (entity == null) {
            return null;
        } else {
            return UserDto.builder()
                    .id(entity.getId())
                    .password(entity.getPassword())
                    .email(entity.getEmail())
                    .build();
        }
    }

    public PurchaseDto toPurchaseDto(PurchaseRequestEntity entity) {
        if (entity == null) {
            return null;
        } else {
            return PurchaseDto.builder()
                    .id(entity.getId())
                    .username(entity.getUsername())
                    .itemId(entity.getItemId())
                    .purchaseDate(entity.getPurchaseDate())
                    .quantity(entity.getQuantity())
                    .build();
        }
    }

    public OrderRequestEntity toEntity(OrderRequest request) {
        if (request == null) {
            return null;
        } else {
            return OrderRequestEntity.builder()
                    .name(request.getName())
                    .nif(request.getNif())
                    .telephone(request.getTelephone())
                    .password(request.getPassword())
                    .email(request.getEmail())
                    .build();
        }
    }

    public PurchaseRequestEntity toPurchaseEntity(PurchaseRequest request) {
        if (request == null) {
            return null;
        } else {
            return PurchaseRequestEntity.builder()
                    .username(request.getUsername())
                    .itemId(request.getItemId())
                    .purchaseDate(request.getPurchaseDate())
                    .quantity(request.getQuantity())
                    .build();
        }
    }

}
