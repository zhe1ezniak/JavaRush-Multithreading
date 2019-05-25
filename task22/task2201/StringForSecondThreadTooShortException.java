package com.javarush.task.task22.task2201;

public class StringForSecondThreadTooShortException extends RuntimeException {
    private String message;
    public StringForSecondThreadTooShortException(String e){
        this.message = e;
    }
    public String getMessage(){
        return this.message;
    }
}
