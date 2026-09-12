package kz.alibek.marketplace.dto.category;

import java.time.LocalDateTime;
import java.util.List;

public record SubcategoryResponseDto(
        Long id,
        String name,
        List<CharacteristicResponseDto> characteristics,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
