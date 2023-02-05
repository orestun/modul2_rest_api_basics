package com.epam.esm.exceptionHandler;

public class ServerException extends RuntimeException{
    public ServerException(String massage){
        super(massage);
    }
}
