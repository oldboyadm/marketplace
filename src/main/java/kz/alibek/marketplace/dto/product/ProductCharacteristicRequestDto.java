package kz.alibek.marketplace.dto.product;

public record ProductCharacteristicRequestDto(
        Long characteristicId,
        String value
) {
}
