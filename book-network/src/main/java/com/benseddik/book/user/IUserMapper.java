package com.benseddik.book.user;

import com.benseddik.book.auth.RegisterRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUserMapper {
    User toEntity(RegisterRequest registerRequest);

    RegisterRequest toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(RegisterRequest registerRequest, @MappingTarget User user);
}