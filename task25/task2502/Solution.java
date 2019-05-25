package com.javarush.task.task25.task2502;

import java.sql.SQLOutput;
import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception {
            //init wheels here
            try {
                wheels = new ArrayList<>();
                if(loadWheelNamesFromDB().length != 4) throw new IllegalArgumentException();
                for (String s : loadWheelNamesFromDB()) {
                    wheels.add(Wheel.valueOf(s));
                }
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException();
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
