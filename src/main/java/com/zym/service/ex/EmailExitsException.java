package com.zym.service.ex;

public class EmailExitsException extends ServiceException {

    public EmailExitsException() {
        super();
    }

    public EmailExitsException(String message) {
        super(message);
    }

    public EmailExitsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailExitsException(Throwable cause) {
        super(cause);
    }

    protected EmailExitsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
