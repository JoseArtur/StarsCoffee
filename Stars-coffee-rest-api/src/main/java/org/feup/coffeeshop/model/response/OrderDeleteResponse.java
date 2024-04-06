package org.feup.coffeeshop.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OrderDeleteResponse implements Serializable {
    private Long deletedCustomerCount;
}
