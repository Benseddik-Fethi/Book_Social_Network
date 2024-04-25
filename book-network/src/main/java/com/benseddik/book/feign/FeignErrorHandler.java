package com.benseddik.book.feign;

import com.benseddik.book.exception.NotFoundException;
import com.benseddik.book.exception.ServiceNotAvailableException;
import com.benseddik.book.exception.UnauthorizedException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;

import java.io.IOException;
import java.io.InputStream;

/**
 * FeignErrorHandler class handles errors from the API.
 * author Fethi Benseddik
 */
@Slf4j
public class FeignErrorHandler implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("API ERROR: {}", response.status());
        PicsurErrorResponse errors = null;
        try (
                InputStream bodyIs = response.body()
                        .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            errors = mapper.readValue(bodyIs, PicsurErrorResponse.class);
            log.error("Error message: {}", errors);
        } catch (IOException e) {
            log.error("Error while decoding error response from Api: {}", e.getMessage());
        }
        String messageError = null;
        if (errors != null) {
            messageError = errors.getData().getMessage();
        }

        return switch (response.status()) {
            case 403 -> new UnauthorizedException(messageError != null ? messageError : "Non autorisé.");
            case 400 -> new NotFoundException(messageError != null ? messageError : "Ressource non trouvée.");
            case 502 -> new ServiceNotAvailableException(messageError != null ? messageError : "Api indisponible.");
            default -> new Exception("Une erreur est survenue sur l'Api.");
        };
    }

}
