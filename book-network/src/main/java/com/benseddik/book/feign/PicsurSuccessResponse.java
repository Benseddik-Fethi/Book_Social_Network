package com.benseddik.book.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PicsurSuccessResponse {
    @JsonProperty("success")
    boolean success;
    @JsonProperty("statusCode")
    int statusCode;
    @JsonProperty("timestamp")
    String timestamp;
    @JsonProperty("timeMs")
    int timeMs;
    @JsonProperty("data")
    PicsurDataSuccessResponse data;
}
