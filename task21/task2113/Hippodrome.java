package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    public static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public static void main(String[] args) {
        game = new Hippodrome(new ArrayList<>());
        game.getHorses().add(new Horse("Horse1", 3, 0));
        game.getHorses().add(new Horse("Horse2", 3, 0));
        game.getHorses().add(new Horse("Horse3", 3, 0));
        game.run();
        game.printWinner();
    }

    public void run(){
        for (int i = 0; i < 100; i++){
            move();
            print();
            try{
                Thread.sleep(200);
            } catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
        }
    }

    public void move(){
        for(int i = 0; i < horses.size(); i++){
            horses.get(i).move();
        }
    }

    public void print(){
        for(int i = 0; i < horses.size(); i++){
            horses.get(i).print();
        }
        for(int i = 0; i < 10; i++){
            System.out.println();
        }
    }

    public Horse getWinner(){
        double maxDistance = 0;
        for (Horse h : horses){
            double dis = h.getDistance();
            if (dis > maxDistance) maxDistance = dis;
        }
        Horse horse = null;
        for (Horse h : horses){
            if (h.getDistance() == maxDistance) horse = h;
        }
        return horse;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
