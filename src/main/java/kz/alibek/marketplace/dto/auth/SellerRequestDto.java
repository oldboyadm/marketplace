package kz.alibek.marketplace.dto.auth;

public record SellerRequestDto (
        String name,
        String email,
        String password,
        String shopTitle,
        String address,
        String phoneNumber
){}



