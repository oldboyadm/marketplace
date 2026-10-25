package kz.alibek.marketplace.handler;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        LocalDateTime date
) {
}
