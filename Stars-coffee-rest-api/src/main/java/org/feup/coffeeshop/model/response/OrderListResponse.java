package org.feup.coffeeshop.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feup.coffeeshop.model.dto.UserDto;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderListResponse implements Serializable {
    private List<UserDto> customers;
}
