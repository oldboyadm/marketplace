package kz.alibek.marketplace.dto.product;

import kz.alibek.marketplace.model.Characteristic;
import kz.alibek.marketplace.model.Subcategory;
import lombok.Data;

import java.util.List;

public record ProductRequestDto(
        String name,
        String description,
        String photoUrl,
        Long price,
        Long subcategoryId,
        List<ProductCharacteristicRequestDto> characteristics
) {
}
