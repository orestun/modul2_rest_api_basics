package com.epam.esm.exceptionHandler;

/**
 * @author orest uzhytchak
 * */
public class ServerException extends RuntimeException{
    public ServerException(String massage){
        super(massage);
    }
}
