package kz.alibek.marketplace.dto;

import kz.alibek.marketplace.model.User;

import java.time.LocalDateTime;

public record AuthResponseDto(
        Long id,
        String name,
        String email,
        String apiKey,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public static AuthResponseDto of(User user) {
        return new AuthResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getApiKey(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}