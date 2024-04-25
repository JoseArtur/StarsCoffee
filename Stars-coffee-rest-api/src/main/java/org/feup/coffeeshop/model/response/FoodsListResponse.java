package org.feup.coffeeshop.model.response;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feup.coffeeshop.model.dto.FoodsDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodsListResponse implements Serializable {
    private List<FoodsDto> foods;
}
