package org.feup.coffeeshop.model.request;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvailableItemsRequest implements Serializable {
    private String itemName;
    private String description;
    private String itemType;
    private String imageUrl;
    private String customizationOptions;
    private String specialAttributes;
}
