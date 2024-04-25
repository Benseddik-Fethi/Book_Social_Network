package com.benseddik.book.book;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.benseddik.book.history.BookTransactionHistory}
 */
public record BorrowedBookResponse(UUID bookUuid, String bookTitle, String bookAuthorName, String bookIsbn,
                                   double bookAverageRating, boolean returned, boolean returnApproved)
        implements Serializable {
}