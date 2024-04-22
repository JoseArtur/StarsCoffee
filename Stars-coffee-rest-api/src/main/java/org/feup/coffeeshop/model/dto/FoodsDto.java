package org.feup.coffeeshop.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodsDto implements Serializable {
    private Long id;
    private String foodName;
    private String description;
    private Double price;
    private String itemType;
    private String imageUrl;
    private Integer isFavorite;
    private Integer isAvailable;
    private Integer quantity;
    private String customizationOptions;
    private String specialAttributes;
}