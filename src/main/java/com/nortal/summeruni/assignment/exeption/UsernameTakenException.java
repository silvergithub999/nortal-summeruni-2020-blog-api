package com.nortal.summeruni.assignment.exeption;

import org.springframework.security.core.AuthenticationException;

public class UsernameTakenException extends AuthenticationException {

    public UsernameTakenException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernameTakenException(String msg) {
        super(msg);
    }
}
