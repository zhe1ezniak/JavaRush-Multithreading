package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        final int mediana;
        if(array.length%2 == 0) mediana = (array[array.length/2 - 1] + array[array.length/2]) / 2;
        else mediana = array[((int) array.length/2)];

        List<Integer> list = new ArrayList<>();
        list = Arrays.asList(array);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(mediana - o1) < Math.abs(mediana - o2)) return -1;
                else if (Math.abs(mediana - o1) > Math.abs(mediana - o2)) return 1;
                else return 0;
                }
        });
        return (Integer[]) list.toArray();
    }
}
