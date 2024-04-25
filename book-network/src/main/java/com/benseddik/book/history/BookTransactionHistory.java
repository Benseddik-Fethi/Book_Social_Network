package com.benseddik.book.history;

import com.benseddik.book.book.Book;
import com.benseddik.book.common.AbstractAuditingEntity;
import com.benseddik.book.user.User;
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
public class BookTransactionHistory extends AbstractAuditingEntity {
    @Serial
    private static final long serialVersionUID = 8042850631903058858L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_seq")
    @SequenceGenerator(name = "history_seq", sequenceName = "history_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private boolean returned;
    private boolean returnApproved;



}
