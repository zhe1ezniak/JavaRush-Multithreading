package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive{
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;
    private List<Human> children = new ArrayList<>();

    private Size size = new Size();

    private BloodGroup bloodGroup;

    public void setBloodGroup(BloodGroup code) {
        bloodGroup = code;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;
    }

    public class Size{
        public int height;
        public int weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void live() {
    }

    public void addChild(Human man){
        children.add(man);
    }

    public void removeChild(Human man){
        children.remove(man);
    }

    public String getPosition(){
        return "Человек";
    }

    public void printData(){
        System.out.println(getPosition() + ": " + name);
    }
}