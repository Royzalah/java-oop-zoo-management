package com.example.zoo.ass3.models;

import com.example.zoo.ass3.exceptions.AgeException;
import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.exceptions.HeightException;
import com.example.zoo.ass3.exceptions.NameException;

import java.io.Serializable;

public class Penguin extends Animal implements Leaderable, Serializable, Comparable<Penguin> {
    public final static int MAX_AGE = 50;
    public final static int MAX_HEIGHT = 220;
    private final static double EAT = 1f;
    public final static int LIFE_EXPECTANCY = 6;
    private String name;
    private int age;
    private double height;
    private Penguin next;
    private boolean leader;

    public Penguin(String name, int age, double height) throws GeneralException {
        super(LIFE_EXPECTANCY);
        setName(name);
        setAge(age);
        setHeight(height);
        this.leader = false;
    }

    public Penguin(Penguin other) {
        super(LIFE_EXPECTANCY);
        name = other.name;
        age = other.age;
        height = other.height;
        leader = other.leader;
    }

    public double feed() {
        double eat = isLeader() ? 2 * EAT : EAT;
        if (next != null) {
            return eat + next.feed();
        }
        return eat;
    }

    @Override
    public String makeNoise() {
        Penguin temp = this;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            sb.append("squack");
            temp = temp.next;
        }
        return sb.toString();
    }

    @Override
    public String getUnit() {
        return "fish";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameException {
        if (name.trim().isEmpty()) {
            throw new NameException();
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws AgeException {
        if (age <= 0 || age > Penguin.MAX_AGE) {
            throw new AgeException();
        }
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) throws HeightException {
        if (height <= 0 || height > Penguin.MAX_HEIGHT) {
            throw new HeightException();
        }
        this.height = height;
    }

    public Penguin getNext() {
        return next;
    }

    public void setNext(Penguin next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return name + ", age: " + age + ", height: " + height + (isLeader() ? ", I can lead the Group" : "");
    }

    @Override
    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    @Override
    public int compareTo(Penguin o) {
        return Double.compare(o.height, height);
    }
}


