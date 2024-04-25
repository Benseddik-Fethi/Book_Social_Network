package com.benseddik.book.feedback;

import com.benseddik.book.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback", description = "Feedback API")
@Slf4j
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<UUID> saveFeedback(
            @RequestBody @Valid FeedbackRequest request,
            Authentication authentication) {
        log.info("Feedback saved: {}", request);
        return ResponseEntity.ok(feedbackService.save(request, authentication));
    }

    @GetMapping("/book/{bookUuid}")
    public ResponseEntity<PageResponse<FeedbackResponse>> findFeedbacksByBook(
            @PathVariable UUID bookUuid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        log.info("Find feedbacks by book: {}", bookUuid);
        return ResponseEntity.ok(feedbackService.findAllFeedbackByBookUuid(bookUuid, page, size, authentication));
    }

}
