package org.feup.coffeeshop.model.request;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoucherRequest implements Serializable {


    private Long id;
    private String pointsRequired;
    private String userId;
    private String voucherCode;
    private String name;
    private String description;
    private Double value;
    private String image;
    private int restaurant;
    private String type;

}
