package net.javaguides.dto.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime localDateTime,
                           String message,
                           String description,
                           String errorCode) {
}
