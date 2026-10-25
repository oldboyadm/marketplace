package kz.alibek.marketplace.dto.auth;

import kz.alibek.marketplace.model.User;

public record SellerAuthResponseDto(
        Long id,
        String name,
        String email,
        String apiKey) {
    public static SellerAuthResponseDto of (User user){
        return new SellerAuthResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getApiKey()
        );
    }
}

