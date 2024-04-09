package org.feup.coffeeshop.model.response;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feup.coffeeshop.model.dto.AvailableItemsDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableItemsListResponse implements Serializable {
    private List<AvailableItemsDto> availableItems;
}
