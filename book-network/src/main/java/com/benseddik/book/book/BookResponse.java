package com.benseddik.book.book;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Book}
 */
public record BookResponse(UUID uuid, String title,
                           String authorName,
                           String isbn,
                           String synopsis,
                           boolean archived,
                           boolean shareable,
                           String bookCover,
                           String ownerFirstName,
                           String ownerLastName,
                           double rate
)
        implements Serializable {
}