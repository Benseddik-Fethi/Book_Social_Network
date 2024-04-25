package com.benseddik.book.exception;

import java.io.Serial;

public class UnauthorizedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5098889579907963166L;

    public UnauthorizedException(String msg) {
        super(msg);
    }
}
