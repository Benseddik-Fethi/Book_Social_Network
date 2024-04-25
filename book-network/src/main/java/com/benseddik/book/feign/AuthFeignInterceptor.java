package com.benseddik.book.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class AuthFeignInterceptor implements RequestInterceptor {

    @Value("${application.picsur.access-key}")
    private String accessKey;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "Api-Key " + accessKey);
    }
}
