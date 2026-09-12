package kz.alibek.marketplace.dto.category;

import kz.alibek.marketplace.model.Category;
import kz.alibek.marketplace.model.Characteristic;
import kz.alibek.marketplace.model.Subcategory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record CategoryResponseDto(
        Long id,
        String name,
        List<SubcategoryResponseDto> subcategories,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CategoryResponseDto of(Category category) {
        List<SubcategoryResponseDto> dtos = new ArrayList<>();
        for (Subcategory subcategory : category.getSubcategories()) {
            List<CharacteristicResponseDto> characteristicDto = new ArrayList<>();
            for (Characteristic chrctr : subcategory.getCharacteristics()) {
                CharacteristicResponseDto characteristicResponseDto = new CharacteristicResponseDto(
                        chrctr.getId(),
                        chrctr.getName(),
                        chrctr.getCreatedAt(),
                        chrctr.getUpdatedAt()
                );
                characteristicDto.add(characteristicResponseDto);
            }
            SubcategoryResponseDto dto = new SubcategoryResponseDto(
                    subcategory.getId(),
                    subcategory.getName(),
                    characteristicDto,
                    subcategory.getCreatedAt(),
                    subcategory.getUpdatedAt()

            );

        }
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                dtos,
                category.getCreatedAt(),
                category.getUpdatedAt()
                );
    }
}
