package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] arraySolution = new Solution[2];
        for(int i = 0; i < arraySolution.length; i++){
                arraySolution[i] = new Solution();
                arraySolution[i].innerClasses[0] = arraySolution[i].new InnerClass();
                arraySolution[i].innerClasses[1] = arraySolution[i].new InnerClass();
        }
        return arraySolution;
    }

    public static void main(String[] args) {

    }
}
