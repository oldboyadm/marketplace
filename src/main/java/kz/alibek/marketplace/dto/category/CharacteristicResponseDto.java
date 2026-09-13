package kz.alibek.marketplace.dto.category;

import kz.alibek.marketplace.model.Characteristic;

import java.time.LocalDateTime;

public record CharacteristicResponseDto(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CharacteristicResponseDto of (Characteristic chrctr){
        return new CharacteristicResponseDto(
                chrctr.getId(),
                chrctr.getName(),
                chrctr.getCreatedAt(),
                chrctr.getUpdatedAt()
        );
    }
}
