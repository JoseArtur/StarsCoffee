package org.feup.coffeeshop.model.request;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoucherRequest implements Serializable {


    private String userId;
    private String voucherCode;
    private Date expiryDate;
    private String pointsRequired;

}
