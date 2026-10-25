package kz.alibek.marketplace.dto.product;

import kz.alibek.marketplace.dto.category.CharacteristicResponseDto;
import kz.alibek.marketplace.model.Characteristic;
import kz.alibek.marketplace.model.Product;
import kz.alibek.marketplace.model.ProductCharacteristic;

import java.time.LocalDateTime;

public record ProductCharacteristicResponseDto(
        CharacteristicResponseDto characteristic,
        Long id,
        String value,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ProductCharacteristicResponseDto of (ProductCharacteristic chrctr){
        return new ProductCharacteristicResponseDto(
                CharacteristicResponseDto.of(chrctr.getCharacteristic()),
                chrctr.getId(),
                chrctr.getValue(),
                chrctr.getCreatedAt(),
                chrctr.getUpdatedAt()
        );
    }

    public static ProductCharacteristicResponseDto withoutCharacteristics (ProductCharacteristic chrctr){
        return new ProductCharacteristicResponseDto(
                null,
                chrctr.getId(),
                chrctr.getValue(),
                chrctr.getCreatedAt(),
                chrctr.getUpdatedAt()
        );
    }
}
