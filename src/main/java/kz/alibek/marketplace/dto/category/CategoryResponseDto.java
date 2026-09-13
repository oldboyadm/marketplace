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
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getSubcategories().stream().map((subcategory -> {
                    return SubcategoryResponseDto.of(subcategory);
                })).toList(),
                category.getCreatedAt(),
                category.getUpdatedAt()
                );
    }
}
