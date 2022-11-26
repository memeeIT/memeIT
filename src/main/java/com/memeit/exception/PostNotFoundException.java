package com.memeit.exception;

public class PostNotFoundException extends RuntimeException{


    public PostNotFoundException(String message) {
        super(message);
    }
}
