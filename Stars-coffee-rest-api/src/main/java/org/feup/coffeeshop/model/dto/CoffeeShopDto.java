package org.feup.coffeeshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeShopDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String telephone;
    private String email;
    private String password;
}
