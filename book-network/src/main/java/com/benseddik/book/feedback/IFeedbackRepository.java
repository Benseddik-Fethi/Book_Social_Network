package com.benseddik.book.feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface  IFeedbackRepository extends JpaRepository<Feedback, Long> {


    @Query("""
            SELECT feedback
            FROM Feedback feedback
            WHERE feedback.book.uuid = :bookUuid""")
    Page<Feedback> findAllByBookUuid(UUID bookUuid, Pageable pageable);
}
