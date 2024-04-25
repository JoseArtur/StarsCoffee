package org.feup.coffeeshop.model.request;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.feup.coffeeshop.model.request.VoucherRequest;
@Data
@Builder
public class SummaryRequest implements Serializable {
    private List<Food> orderItems;
    private PaymentMethod paymentMethod;
    private String totalCost;
    private String userEmail;
    private List<VoucherRequest> vouchersUsed;

    @Data
    @Builder
    public static class Food implements Serializable {
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
    public static class PaymentMethod implements Serializable {
        private int pIcon;
        private int pId;
        private String pName;
    }
}