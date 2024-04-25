package com.benseddik.book.exception;

import java.io.Serial;

public class OperationNotPermittedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -4400549368600405769L;

    public OperationNotPermittedException(String msg) {
        super(msg);
    }
}
