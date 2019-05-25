package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        recursExcept(e);
    }

    public void recursExcept(Throwable e){
        if(e.getCause() != null){
            recursExcept(e.getCause());
        }
        System.out.println(e.getClass().toString().substring(6) + ": " + e.getMessage());
    }

    public static void main(String[] args) {
    }
}
