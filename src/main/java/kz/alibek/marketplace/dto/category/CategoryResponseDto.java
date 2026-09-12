package kz.alibek.marketplace.dto.category;

import java.time.LocalDateTime;
import java.util.List;

public record CategoryResponseDto(
        Long id,
        String name,
        List<SubcategoryResponseDto> subcategories,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
