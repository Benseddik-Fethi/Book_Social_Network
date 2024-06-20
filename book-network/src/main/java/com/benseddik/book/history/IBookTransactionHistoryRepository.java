package com.benseddik.book.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IBookTransactionHistoryRepository extends
        JpaRepository<BookTransactionHistory, Long> {


    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.userId = :uuid
            """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, String uuid);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.book.createdBy = :uuid
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, String uuid);

    @Query("""
            SELECT (COUNT(*) > 0) AS isBorrowed
            FROM BookTransactionHistory history
            WHERE history.userId = :userUuid
            AND history.book.uuid = :bookUuid
            AND history.returnApproved = false
            """)
    boolean isAlreadyBorrowedByUser(String userUuid, UUID bookUuid);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.userId = :userUuid
            AND history.book.uuid = :bookUuid
            AND history.returned = false
            AND history.returnApproved = false
            """)
    Optional<BookTransactionHistory> findByBookUuidAndUserUuid(String userUuid, UUID bookUuid);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.book.uuid = :bookUuid
            AND history.book.createdBy = :ownerUuid
            AND history.returned = true
            AND history.returnApproved = false
            """)
    Optional<BookTransactionHistory> findByBookUuidAndOwnerUuid(UUID bookUuid, String ownerUuid);
}
