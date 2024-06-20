package com.benseddik.book.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IBookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {


    Optional<Book> findByUuid(UUID uuid);

    @Query("""
                        SELECT book
                        FROM Book book
                        WHERE book.archived = false
                        AND book.shareable = true
                        AND book.createdBy != :id
            """)
    Page<Book> findAllDisplayableBooks(Pageable pageable, String id);
}
