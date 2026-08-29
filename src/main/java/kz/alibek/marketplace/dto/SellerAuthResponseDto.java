package kz.alibek.marketplace.dto;

public record SellerAuthResponseDto(
        Long id,
        String name,
        String email,
        String apiKey) {}

