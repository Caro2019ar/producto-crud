package com.example.demo.exception;

public class NoEncontradoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NoEncontradoException(String message) {
        super(message);
    }

    public NoEncontradoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
