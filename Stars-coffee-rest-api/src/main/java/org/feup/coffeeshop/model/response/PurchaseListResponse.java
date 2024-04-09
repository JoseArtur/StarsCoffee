package org.feup.coffeeshop.model.response;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feup.coffeeshop.model.dto.AvailableItemsDto;
import org.feup.coffeeshop.model.dto.PurchaseDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseListResponse implements Serializable {
    private List<PurchaseDto> purchaseDtoList;
}
