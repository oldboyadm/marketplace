package kz.alibek.marketplace.dto.category;

import java.util.List;

public record CategoryRequestDto(
        String name,
        List<SubcategoryRequestDto> subcategories
) {
}
