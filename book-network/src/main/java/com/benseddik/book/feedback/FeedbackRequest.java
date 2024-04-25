package com.benseddik.book.feedback;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Feedback}
 */
public record FeedbackRequest(
        @Positive(message = "200")
        @Min(value = 0, message = "201")
        @Max(value = 5, message = "202")
        Double note,
        @NotNull(message = "203")
        @NotEmpty(message = "203")
        @NotBlank(message = "203")
        String comment,
        @NotNull(message = "204")
        UUID bookUuid) implements Serializable {
}