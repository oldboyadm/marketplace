package kz.alibek.marketplace.dto;

public record LoginRequestDto(
        String name,
        String email,
        String password
) {
}
