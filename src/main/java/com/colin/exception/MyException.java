package com.colin.exception;

import org.apache.shiro.authc.AuthenticationException;

public class MyException extends AuthenticationException {
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
