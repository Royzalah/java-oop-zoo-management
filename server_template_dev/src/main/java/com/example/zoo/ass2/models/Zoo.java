package com.example.zoo.ass2.models;

import com.example.zoo.ass2.exceptions.GeneralException;
import com.example.zoo.ass2.general.Address;
import com.example.zoo.wrapper.service.ZooInitializer;

public class Zoo implements ZooInitializer {
    private final String name;
    private final Address address;
    private Penguin penguin;

    public Zoo(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getZooTitle() {
        return name + ", " + address;
    }

    @Override
    public void initializeZoo() throws GeneralException {
        // TODO need to implements
    }

    public int[] getPredatorSummary() {
        // TODO need to implements
        return new int[]{0, 0};
    }

    public int getPenguinSummary() {
        // TODO need to implements
        return 0;
    }

    public int[] getFishSummary() {
        // TODO need to implements
        return new int[]{0, 0, 0};
    }

    public String createFish(int amount) throws GeneralException {
        // TODO need to implements
        return "not working.. need to implements";
    }

    public Predator[][] getPredators() {
        // TODO need to implements
        return new Predator[2][0];
    }

    public Penguin[] getPenguins() {
        // TODO need to implements
        return new Penguin[0];
    }

    public Fish[][] getFish() {
        // TODO need to implements
        return new Fish[3][0];
    }

    public String feedAnimals() {
        StringBuilder sb = new StringBuilder("not working.. need to implements");
        // TODO need to implements
        return sb.toString();
    }
    public String showAnimalsSounds() {
        StringBuilder sb = new StringBuilder("not working.. need to implements");
        // TODO need to implements
        return sb.toString();
    }
}
