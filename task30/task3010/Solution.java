package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            List<Integer> list = new ArrayList();
            BigInteger value = null;
            if (args[0].matches("\\W")) System.out.println("incorrect");
            else {
                for (int i = 2; i < 37; i++) {
                    try {
                        value = new BigInteger(args[0], i);
                    } catch (Exception e) {

                    }
                    if (value != null)
                        list.add(i);
                }
                if (list.size() == 0) System.out.println("incorrect");
                else {
                    System.out.println(list.get(0));
                }
            }
        }catch(Exception e){}
    }
}