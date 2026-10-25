package kz.alibek.marketplace.dto.category;

import java.util.List;

public record SubcategoryRequestDto(
    String name,
    List<String> characteristics
) {
}
