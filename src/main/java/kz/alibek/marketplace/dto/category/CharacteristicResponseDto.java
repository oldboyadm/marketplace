package kz.alibek.marketplace.dto.category;

import java.time.LocalDateTime;

public record CharacteristicResponseDto(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
