package com.benseddik.book.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Book}
 */
public record BookRequest(UUID uuid,
                          @NotNull(message = "100") @NotEmpty(message = "100") String title,
                          @NotNull(message = "101") @NotEmpty(message = "101") String authorName,
                          @NotNull(message = "102") @NotEmpty(message = "102") String isbn,
                          @NotNull(message = "103") @NotEmpty(message = "103") String synopsis,
                          boolean shareable)
        implements Serializable {
}