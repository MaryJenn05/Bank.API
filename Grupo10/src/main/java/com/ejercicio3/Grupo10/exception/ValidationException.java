package com.ejercicio3.Grupo10.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(){
        super();
    }

    public ValidationException(String message){
        super(message);
    }
}
