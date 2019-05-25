package com.javarush.task.task22.task2201;

public class StringForFirstThreadTooShortException extends RuntimeException {
    private String message;
    public StringForFirstThreadTooShortException(String e){
        this.message = e;
    }
    public String getMessage(){
        return this.message;
    }
}
