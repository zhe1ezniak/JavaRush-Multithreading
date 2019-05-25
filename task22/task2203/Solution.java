package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        String result;
        try{
            int firstIndex = string.indexOf("\t");
            result = string.substring(firstIndex + 1);
            int secondIndex = result.indexOf("\t");
            result = result.substring(0, secondIndex);
        } catch (Exception e){
            throw new TooShortStringException();
        }
        return result;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
