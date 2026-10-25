package kz.alibek.marketplace.dto.auth;

public record UserRequestDto(
        String name,
        String email,
        String password
) {
}
