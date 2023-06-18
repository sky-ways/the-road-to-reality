package com.github.cao.awa.trtr.framework.exception;

public class NotPreparedException extends InvertOfControlException {
    public NotPreparedException(String message) {
        super(msg(message));
    }

    public NotPreparedException(String message, Throwable cause) {
        super(msg(message),
              cause
        );
    }

    public NotPreparedException(Throwable cause) {
        super(cause);
    }

    protected NotPreparedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg(message),
              cause,
              enableSuppression,
              writableStackTrace
        );
    }

    public NotPreparedException() {
        super(msg());
    }

    private static String msg(String msg) {
        return msg() + ", " + msg;
    }

    private static String msg() {
        return "Something maybe fields or methods are not prepared to invert of control";
    }
}
