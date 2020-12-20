package com.anatoliapark.nursinghome.exception;

public final class BussinessException extends RuntimeException {

    private static final long serialVersionUID = 51514312562L;

    public BussinessException() {
        super();
    }

    public BussinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BussinessException(final String message) {
        super(message);
    }

    public BussinessException(final Throwable cause) {
        super(cause);
    }

}
