package org.feup.coffeeshop.model.request;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseRequest implements Serializable {

    private String username;
    private String itemId;
    private Date purchaseDate;
    private String quantity;

}
