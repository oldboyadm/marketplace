package kz.alibek.marketplace.dto;

import lombok.Data;

public record SellerRequestDto (
        String name,
        String email,
        String password,
        String shopTitle,
        String address,
        String phoneNumber
){}



