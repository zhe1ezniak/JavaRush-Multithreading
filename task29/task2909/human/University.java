package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
        this.students = new ArrayList();
    }

    public Student getStudentWithAverageGrade(double grade) {
        //TODO:
        for(Student std : students){
            if (grade == std.getAverageGrade()) return std;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        double maxAverageGrade = 0;
        Student maxStudent = null;

        for(Student std : students){
            if (maxAverageGrade < std.getAverageGrade()) {
                maxAverageGrade = std.getAverageGrade();
                maxStudent = std;
            }
        }
        return maxStudent;
    }

    public Student getStudentWithMinAverageGrade(){
        double minAverageGrade = students.get(0).getAverageGrade();
        Student minStudent = null;

        for(Student std : students){
            if (minAverageGrade > std.getAverageGrade()) {
                minAverageGrade = std.getAverageGrade();
                minStudent = std;
            }
        }
        return minStudent;
    }

    public void expel(Student student){
        students.remove(student);
    }
}