package kz.alibek.marketplace.dto.auth;

import kz.alibek.marketplace.model.User;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UserResponseDto of(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
