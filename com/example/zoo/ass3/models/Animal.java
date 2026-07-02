package com.example.zoo.ass3.models;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

public abstract class Animal implements Serializable {
    public final static int MAX_HAPPINESS = 100;
    private static final int MIN_HAPPINESS_RANDOM = 15;
    private static final int MAX_HAPPINESS_RANDOM = 30;
    protected final String id;
    protected int age;
    protected int lifeExpectancy;
    protected int happiness;

    public Animal(int lifeExpectancy) {
        id = UUID.randomUUID().toString();
        this.lifeExpectancy = lifeExpectancy;
        happiness = MAX_HAPPINESS;
    }


    public abstract double feed();

    public double feedAnimal() {
        happiness = MAX_HAPPINESS;
        return this.feed();
    }

    public abstract String makeNoise();

    public abstract String getUnit();

    public boolean ageOneYear() {
        age++;
        if (age > lifeExpectancy) {
            return false;
        }
        happiness -= (MIN_HAPPINESS_RANDOM + new Random().nextInt(MAX_HAPPINESS_RANDOM - MIN_HAPPINESS_RANDOM));
        return happiness > 0;
    }


}
