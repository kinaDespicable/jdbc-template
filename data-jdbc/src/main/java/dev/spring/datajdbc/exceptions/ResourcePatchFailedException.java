package dev.spring.datajdbc.exceptions;

public class ResourcePatchFailedException extends RuntimeException{
    public ResourcePatchFailedException() {
    }

    public ResourcePatchFailedException(String message) {
        super(message);
    }

    public ResourcePatchFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourcePatchFailedException(Throwable cause) {
        super(cause);
    }

    public ResourcePatchFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
