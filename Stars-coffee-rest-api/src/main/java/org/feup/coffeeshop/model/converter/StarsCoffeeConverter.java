package org.feup.coffeeshop.model.converter;

import org.feup.coffeeshop.model.dto.AvailableItemsDto;
import org.feup.coffeeshop.model.dto.PurchaseDto;
import org.feup.coffeeshop.model.dto.UserDto;
import org.feup.coffeeshop.model.dto.VoucherDto;
import org.feup.coffeeshop.model.entity.AvailableItemsRequestEntity;
import org.feup.coffeeshop.model.entity.LoginRequestEntity;
import org.feup.coffeeshop.model.entity.OrderRequestEntity;
import org.feup.coffeeshop.model.entity.PurchaseRequestEntity;
import org.feup.coffeeshop.model.entity.VoucherRequestEntity;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.request.PurchaseRequest;
import org.feup.coffeeshop.model.request.VoucherRequest;
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

    public AvailableItemsDto toAvailableItemsDto(AvailableItemsRequestEntity entity) {
        if (entity == null) {
            return null;
        } else {
            return AvailableItemsDto.builder()
                    .id(entity.getId())
                    .itemName(entity.getItemName())
                    .description(entity.getDescription())
                    .itemType(entity.getItemType())
                    .price(entity.getPrice())
                    .imageUrl(entity.getImageUrl())
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

    public VoucherDto toVoucherDto(VoucherRequestEntity entity) {
        if (entity == null) {
            return null;
        } else {
            return VoucherDto.builder()
                    .id(entity.getId())
                    .pointsRequired(entity.getPointsRequired())
                    .voucherCode(entity.getVoucherCode())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .value(entity.getValue())
                    .image(entity.getImage())
                    .restaurant(entity.getRestaurant())
                    .type(entity.getType())
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
