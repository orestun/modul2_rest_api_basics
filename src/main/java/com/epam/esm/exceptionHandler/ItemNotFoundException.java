package com.epam.esm.exceptionHandler;

/**
 * @author orest uzhytchak
 * */
public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String message) {
        super(message);
    }
}
