package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName = reader.readLine();
            reader.close();
            reader = new BufferedReader(new FileReader(fileName));
            StringBuilder builder = new StringBuilder();
            while (reader.ready()){
                builder.append(reader.readLine()).append(" ");
            }
            reader.close();

            String[] arrayLines = builder.toString().split(" ");
            ArrayList<String> list = new ArrayList<>();
            for(String s : arrayLines){
                list.add(s);
            }
            for( int i = 0; i < list.size(); i ++){
                for (int j = 0; j < list.size(); j ++){
                    if (list.get(i) == null || list.get(j) == null) continue;
                    StringBuilder sb = new StringBuilder(list.get(i)).reverse();
                    Pair p = new Pair();
                    if (sb.toString().equals(list.get(j)) && i != j){
                        p.first = list.get(i);
                        p.second = list.get(j);
                        result.add(p);
                        list.set(i, null);
                        list.set(j, null);
                    }
                }
            }
            for(Pair p : result){
                System.out.println(p.toString());
            }
        } catch (IOException e){
        }
        for(Pair p : result){
            System.out.println(p);
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
