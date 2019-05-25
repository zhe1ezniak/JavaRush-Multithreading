package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        String line = "";
        if (a > b) {
            for(; a > b; a--){
                line += a + " ";
            }
            return line + b;
        } else if (a == b) {
                return Integer.toString(a);
            }
        else {
            for (; a < b; a++){
                line += a + " ";
            }
            return line + b;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(100);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}