package com.benseddik.book.book;

import com.benseddik.book.common.AbstractAuditingEntity;
import com.benseddik.book.feedback.Feedback;
import com.benseddik.book.history.BookTransactionHistory;
import com.benseddik.book.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends AbstractAuditingEntity {
    @Serial
    private static final long serialVersionUID = 8082584980272168772L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String authorName;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "synopsis", nullable = false)
    private String synopsis;

    @Column(name = "book_cover")
    private String bookCover;

    @Column(name = "archived", nullable = false)
    private boolean archived;

    @Column(name = "shareable", nullable = false)
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private Set<BookTransactionHistory> bookTransactionHistories = new LinkedHashSet<>();

    @Transient
    public double getAverageRating() {
        if(feedbacks.isEmpty()) {
            return 0.0;
        }
        var rate =  feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        return Math.round(rate * 10.0) / 10.0;
    }
}
