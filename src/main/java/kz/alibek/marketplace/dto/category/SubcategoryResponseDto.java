package kz.alibek.marketplace.dto.category;

import kz.alibek.marketplace.model.Subcategory;

import java.time.LocalDateTime;
import java.util.List;

public record SubcategoryResponseDto(
        Long id,
        String name,
        List<CharacteristicResponseDto> characteristics,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static SubcategoryResponseDto of (Subcategory subcategory){
        return new SubcategoryResponseDto(
                subcategory.getId(),
                subcategory.getName(),
                subcategory.getCharacteristics().stream().map(
                        (characteristic)->{
                            return CharacteristicResponseDto.of(characteristic);
                        }).toList(),
                subcategory.getCreatedAt(),
                subcategory.getUpdatedAt()
        );
    }
}
