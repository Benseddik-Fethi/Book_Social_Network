package com.benseddik.book.file;

import com.benseddik.book.feign.AuthFeignInterceptor;
import com.benseddik.book.feign.PicsurSuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "picsur-api", url = "${application.picsur.url}", configuration = AuthFeignInterceptor.class)
public interface IPicsurApiService {

    @PostMapping(value = "/api/image/upload",
            consumes = "multipart/form-data")
    ResponseEntity<PicsurSuccessResponse> uploadImage(MultipartFile image);

}
