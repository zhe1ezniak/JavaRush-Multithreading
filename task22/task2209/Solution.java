package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()){
            String[] array = reader.readLine().split(" ");
            for (int i = 0 ; i < array.length; i++){
                list.add(array[i]);
            }
        }
        reader.close();
        String[] words = (String[]) list.toArray();
        StringBuilder result = getLine(words);
        System.out.println(result.toString().trim());
    }

    public static StringBuilder getLine(String... words) {
        return null;
    }

}
