package com.benseddik.book.feedback;

import java.io.Serializable;

/**
 * DTO for {@link Feedback}
 */
public record FeedbackResponse(Double note, String comment, boolean ownFeedback) implements Serializable {
}