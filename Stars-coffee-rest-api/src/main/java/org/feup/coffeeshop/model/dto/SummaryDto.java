package org.feup.coffeeshop.model.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.feup.coffeeshop.model.dto.VoucherDto;

@Data
@Builder
public class SummaryDto implements Serializable {
    private List<FoodDto> orderItems;
    private PaymentMethodDto paymentMethod;
    private String totalCost;
    private String userEmail;
    private List<VoucherDto> vouchersUsed;

    @Data
    @Builder
    public static class FoodDto implements Serializable {
        private String customizationOptions;
        private String description;
        private String foodName;
        private int id;
        private String imageUrl;
        private int isAvailable;
        private int isFavorite;
        private String itemType;
        private double price;
        private int quantity;
        private String specialAttributes;
    }

    @Data
    @Builder
    public static class PaymentMethodDto implements Serializable {
        private int pIcon;
        private int pId;
        private String pName;
    }

}