package kz.alibek.marketplace.dto.auth;

public record LoginRequestDto(
        String email,
        String password
) {
}
