package com.benseddik.book.book;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IBookMapper {
    Book toEntity(BookRequest bookRequest);

    BookRequest toBookRequest(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book partialUpdate(
            BookRequest bookRequest, @MappingTarget Book book);

    @Mapping(source = "ownerLastName", target = "owner.lastName")
    @Mapping(source = "ownerFirstName", target = "owner.firstName")
    Book toEntity(BookResponse bookResponse);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "rate", expression = "java(book.getAverageRating())")
    BookResponse toBookResponse(Book book);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book partialUpdate(
            BookResponse bookResponse, @MappingTarget Book book);
}