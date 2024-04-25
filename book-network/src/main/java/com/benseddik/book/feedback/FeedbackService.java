package com.benseddik.book.feedback;

import com.benseddik.book.book.Book;
import com.benseddik.book.book.IBookRepository;
import com.benseddik.book.common.PageResponse;
import com.benseddik.book.exception.OperationNotPermittedException;
import com.benseddik.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final IBookRepository bookRepository;
    private final IFeedbackRepository feedbackRepository;
    private final IFeedbackMapper feedbackMapper;

    public UUID save(FeedbackRequest request, Authentication authentication) {
        Book book = bookRepository.findByUuid(request.bookUuid())
                .orElseThrow(() -> new EntityNotFoundException("Book not found : " + request.bookUuid()));
        if (!book.isShareable() || book.isArchived()) {
            throw new OperationNotPermittedException("You cannot give a feedback on");
        }
        User user = ((User) authentication.getPrincipal());
        if (book.getOwner().getId().equals(user.getId())) {
            throw new OperationNotPermittedException("You cannot give a feedback on your own book");
        }
        Feedback feedback = feedbackMapper.toEntity(request);
        feedback.setBook(book);
        return feedbackRepository.save(feedback).getUuid();
    }

    public PageResponse<FeedbackResponse> findAllFeedbackByBookUuid(UUID bookUuid, int page, int size,
                                                                    Authentication authentication) {
        Pageable pageable = PageRequest.of(page, size);
        User user = ((User) authentication.getPrincipal());
        Page<Feedback> feedbacks = feedbackRepository.findAllByBookUuid(bookUuid, pageable);
        List<FeedbackResponse> feedbackResponses = feedbacks.stream()
                .map(feedback -> feedbackMapper.toFeedbackResponse(feedback, user.getUuid().toString()))
                .toList();
        return new PageResponse<>(feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast());
    }
}
