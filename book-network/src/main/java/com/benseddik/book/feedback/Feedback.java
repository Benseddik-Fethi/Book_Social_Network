package com.benseddik.book.feedback;

import com.benseddik.book.book.Book;
import com.benseddik.book.common.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback extends AbstractAuditingEntity {

    @Serial
    private static final long serialVersionUID = -8967222159075775128L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_seq")
    @SequenceGenerator(name = "feedback_seq", sequenceName = "feedback_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "id", nullable = false)
    private Long id;
    
    private Double note;
    
    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
