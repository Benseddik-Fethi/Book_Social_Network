package com.benseddik.book.exception;

import java.io.Serial;

public class ServiceNotAvailableException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -110831692916420283L;

    public ServiceNotAvailableException(String msg) {
        super(msg);
    }
}
