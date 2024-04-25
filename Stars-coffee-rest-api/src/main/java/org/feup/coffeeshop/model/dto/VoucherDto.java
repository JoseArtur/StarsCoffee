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
    private String userId;
    private String pointsRequired;
    private String voucherCode;
    private String name;
    private String description;
    private Double value;
    private String image;
    private int restaurant;
    private String type;
}
