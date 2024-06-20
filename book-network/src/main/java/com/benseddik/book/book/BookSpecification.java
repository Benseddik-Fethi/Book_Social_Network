package com.benseddik.book.book;


import org.springframework.data.jpa.domain.Specification;


public class BookSpecification {

    public static Specification<Book> withOwnerUuid(String userName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Book_.createdBy), userName);
    }
}
