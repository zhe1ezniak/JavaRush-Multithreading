package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        String result;
        try{
            String [] arrayString = string.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < 5; i++){
                sb.append(arrayString[i]).append(" ");
            }
            result = sb.toString().trim();
        } catch (Exception e){
            throw new TooShortStringException();
        }
        return result;
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
