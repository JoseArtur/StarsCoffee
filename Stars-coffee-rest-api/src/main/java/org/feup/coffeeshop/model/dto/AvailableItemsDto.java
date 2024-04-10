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
public class AvailableItemsDto implements Serializable {
    private Long id;
    private String itemName;
    private String description;
    private String itemType;
    private String price;
    private String imageUrl;
    private String customizationOptions;
    private String specialAttributes;
}
