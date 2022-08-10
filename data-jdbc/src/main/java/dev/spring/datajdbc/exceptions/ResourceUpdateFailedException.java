package dev.spring.datajdbc.exceptions;

public class ResourceUpdateFailedException extends RuntimeException{
    public ResourceUpdateFailedException() {
    }

    public ResourceUpdateFailedException(String message) {
        super(message);
    }

    public ResourceUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceUpdateFailedException(Throwable cause) {
        super(cause);
    }

    public ResourceUpdateFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
