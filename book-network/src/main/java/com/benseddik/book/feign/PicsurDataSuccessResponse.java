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
public class PicsurDataSuccessResponse {
    @JsonProperty("id")
private String id;
@JsonProperty("user_id")
private String userId;
@JsonProperty("created")
private String created;
@JsonProperty("file_name")
private String fileName;
@JsonProperty("expires_at")
private String expiresAt;
@JsonProperty("delete_key")
private String deleteKey;
}
