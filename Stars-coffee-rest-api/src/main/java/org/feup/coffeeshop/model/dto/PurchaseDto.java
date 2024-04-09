package org.feup.coffeeshop.model.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto implements Serializable {
    private Long id;
    private String username;
    private String itemId;
    private Date purchaseDate;
    private String quantity;
}
