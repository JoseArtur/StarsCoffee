package org.feup.coffeeshop.model.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDto implements Serializable {
    private Long id;
    private String username;
    private String itemId;
    private Date purchaseDate;
    private String quantity;
}
