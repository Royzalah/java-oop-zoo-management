package com.example.zoo.ass2.manage;

import com.example.zoo.ass2.exceptions.GeneralException;
import com.example.zoo.ass2.general.Address;
import com.example.zoo.ass2.models.Fish;
import com.example.zoo.ass2.models.Penguin;
import com.example.zoo.ass2.models.Predator;
import com.example.zoo.ass2.models.Zoo;
import com.example.zoo.wrapper.service.ZooService;

public class Manage implements ZooService {
    private static Zoo zoo;

    @Override
    public void init() {
        try {
            zoo = new Zoo("TVLZoo", new Address("Tel Aviv", "Bazel", "22"));
            zoo.initializeZoo();
        } catch (GeneralException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getZooTitle() {
        return zoo.getZooTitle();
    }

    @Override
    public Object[][] showZoo() {
        Object[][] data = new Object[4][1];
        data[0][0] = getZooTitle();
        data[1][0] = zoo.getPredatorSummary();
        data[2][0] = zoo.getPenguinSummary();
        data[3][0] = zoo.getFishSummary();
        return data;
    }

    @Override
    public String addPenguin(String name, int age, double height) {
        // TODO need to implements
        return "not working.. need to implements";
    }

    @Override
    public String addPredator(String name, int age, double weight, String gender, String type) {
        // TODO need to implements
        return "not working.. need to implements";
    }

    @Override
    public String createFish(int age, double length, String pattern, String[] colors, String type) {
        // TODO need to implements
        return "not working.. need to implements";
    }

    @Override
    public String createRandomFish(int amount) {
        try {
            return zoo.createFish(amount);
        } catch (GeneralException e) {
            return "Failed to create random fish, " + e.getMessage() + ", Please try again..";
        }
    }

    @Override
    public String getDominantColors() {
        // TODO need to implements
        return "not working.. need to implements";
    }

    @Override
    public Predator[][] getPredators() {
        return zoo.getPredators();
    }

    @Override
    public Penguin[] getPenguins() {
        return zoo.getPenguins();
    }

    @Override
    public Fish[][] getFishes() {
        return zoo.getFish();
    }

    @Override
    public String feedAll() {
        return zoo.feedAnimals();
    }

    @Override
    public String showAnimalsSounds() {
        return zoo.showAnimalsSounds();
    }

}
