package com.quillmates.quillmatesbackend.exceptions;

public class JwtAuthenticationException extends RuntimeException {

    public JwtAuthenticationException(String invalidToken) {
    }

    public JwtAuthenticationException() {
    }
}
