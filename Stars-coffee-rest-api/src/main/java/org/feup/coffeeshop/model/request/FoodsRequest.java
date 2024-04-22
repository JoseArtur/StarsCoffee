package org.feup.coffeeshop.model.request;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodsRequest implements Serializable {
    private String foodName;
    private String description;
    private Integer price;
    private String itemType;
    private String imageUrl;
    private Integer isFavorite;
    private Integer isAvailable;
    private Integer quantity;
    private String customizationOptions;
    private String specialAttributes;
}
