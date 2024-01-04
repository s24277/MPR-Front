package com.pjatk.MPR.model;



public class Cow {

    private Long id;
    private String name;
    private int age;

    public Cow(String name, int age) {
        this.name = name;
            this.age = age;
    }

    public Cow() {

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
}
