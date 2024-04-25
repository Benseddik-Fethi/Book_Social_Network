package com.benseddik.book.book;

import com.benseddik.book.user.User_;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> withOwnerUuid(String uuid) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Book_.owner).get(User_.uuid), uuid);
    }
}
