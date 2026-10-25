package kz.alibek.marketplace.dto.product;

import kz.alibek.marketplace.dto.category.SubcategoryResponseDto;
import kz.alibek.marketplace.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public record ProductResponseDto(
        Long id,
        String name,
        String description,
        String photoUrl,
        Long price,
        SubcategoryResponseDto subcategory,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<ProductCharacteristicResponseDto> characteristics
) {
    public static ProductResponseDto of(Product product){
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPhotoUrl(),
                product.getPrice(),
                SubcategoryResponseDto.withoutCharacteristics(product.getSubcategory()),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getProductCharacteristics()
                        .stream()
                        .map(ProductCharacteristicResponseDto::of)
                        .toList()
        );
    }
}
