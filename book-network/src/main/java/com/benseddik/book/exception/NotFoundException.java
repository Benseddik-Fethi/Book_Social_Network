package com.benseddik.book.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -8233986231842518925L;

    public NotFoundException(String msg) {
        super(msg);
    }
}
