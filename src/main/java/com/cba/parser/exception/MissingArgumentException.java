package com.cba.parser.exception;

public class MissingArgumentException extends RuntimeException{
    public MissingArgumentException(String message){
        super(message);
    }
}

