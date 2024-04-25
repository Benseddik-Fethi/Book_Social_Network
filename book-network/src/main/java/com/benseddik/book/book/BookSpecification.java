package com.benseddik.book.book;

import com.benseddik.book.user.User_;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class BookSpecification {

    public static Specification<Book> withOwnerUuid(UUID uuid) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Book_.owner).get(User_.uuid), uuid);
    }
}
