package com.benseddik.book.history;

import com.benseddik.book.book.BorrowedBookResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IBookTransactionHistoryMapper {
    @Mapping(source = "bookIsbn", target = "book.isbn")
    @Mapping(source = "bookAuthorName", target = "book.authorName")
    @Mapping(source = "bookTitle", target = "book.title")
    @Mapping(source = "bookUuid", target = "book.uuid")
    BookTransactionHistory toEntity(BorrowedBookResponse borrowedBookResponse);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "bookAverageRating", expression = "java(bookTransactionHistory.getBook().getAverageRating())")
    BorrowedBookResponse toDto(
            BookTransactionHistory bookTransactionHistory);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookTransactionHistory partialUpdate(
            BorrowedBookResponse borrowedBookResponse, @MappingTarget BookTransactionHistory bookTransactionHistory);
}