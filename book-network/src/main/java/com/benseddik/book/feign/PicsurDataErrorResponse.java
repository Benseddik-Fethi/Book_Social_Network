package com.benseddik.book.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PicsurDataErrorResponse {
    @JsonProperty("type")
    String type;
    @JsonProperty("message")
    String message;
}
