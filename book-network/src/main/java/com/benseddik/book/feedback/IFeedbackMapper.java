package com.benseddik.book.feedback;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IFeedbackMapper {
    @Mapping(source = "bookUuid", target = "book.uuid")
    Feedback toEntity(FeedbackRequest feedbackRequest);

    @Mapping(source = "book.uuid", target = "bookUuid")
    FeedbackRequest toFeedbackRequest(Feedback feedback);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "bookUuid", target = "book.uuid")
    Feedback partialUpdate(FeedbackRequest feedbackRequest, @MappingTarget Feedback feedback);

    Feedback toEntity(FeedbackResponse feedbackResponse);

    FeedbackResponse toFeedbackResponse(Feedback feedback);

    @Mapping(target = "ownFeedback",
            expression = "java(java.util.UUID.fromString(feedback.getBook().getCreatedBy())" +
                    ".equals(java.util.UUID.fromString(userUuid)))")
    FeedbackResponse toFeedbackResponse(Feedback feedback, String userUuid);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Feedback partialUpdate(
            FeedbackResponse feedbackResponse, @MappingTarget Feedback feedback);
}