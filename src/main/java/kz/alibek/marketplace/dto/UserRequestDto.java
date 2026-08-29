package kz.alibek.marketplace.dto;

public record UserRequestDto(
        String name,
        String email,
        String password
) {
}
