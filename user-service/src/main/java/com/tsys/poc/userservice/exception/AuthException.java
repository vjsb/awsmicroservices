package com.tsys.poc.userservice.exception;

public class AuthException extends RuntimeException {

    private static final long serialVersionUID = 4091993317181374328L;

    public AuthException(String exMessage) {
        super(exMessage);
    }
}
