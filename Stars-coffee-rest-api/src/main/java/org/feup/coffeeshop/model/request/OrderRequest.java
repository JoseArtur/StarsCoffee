package org.feup.coffeeshop.model.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OrderRequest implements Serializable {
    private String name;
    private String surname;
    private String telephone;
    private String email;
    private String password;
}
