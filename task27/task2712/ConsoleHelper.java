package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishList = new ArrayList<>();
        Dish.allDishesToString();
        writeMessage("Выберите блюдо. Для завершения заказа, введите: \"exit\"");
        while(true){
            String choice = readString();
            if(choice.equals("exit")) break;
            try{
                dishList.add(Dish.valueOf(choice));
            } catch (IllegalArgumentException e){
                writeMessage("Выбраного блюда в меню нет. Выберите другое.");
            }
        }
        return dishList;
    }
}
