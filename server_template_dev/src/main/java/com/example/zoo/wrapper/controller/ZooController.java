package com.example.zoo.wrapper.controller;

import com.example.zoo.ass2.models.Fish;
import com.example.zoo.ass2.models.Penguin;
import com.example.zoo.ass2.models.Predator;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.zoo.wrapper.ZooApplication.manage;


@RestController
@RequestMapping("/api")
public class ZooController {
    @GetMapping("/zoo")
    public Map<String, Object> getZooData() {
        Object[][] zooData = manage.showZoo();
        Map<String, Object> result = new HashMap<>();
        result.put("details", Arrays.asList(zooData[0]));
        result.put("predators", Arrays.asList(zooData[1]));
        result.put("penguins", Arrays.asList(zooData[2]));
        result.put("fish", Arrays.asList(zooData[3]));
        return result;
    }

    @GetMapping("/penguins")
    public List<Penguin> getPenguins() {
        return Arrays.asList(manage.getPenguins());
    }

    @GetMapping("/predators")
    public List<List<Predator>> getPredators() {
        Predator[][] predators = manage.getPredators();
        return Arrays.stream(predators)
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }

    @GetMapping("/fish")
    public List<List<Fish>> getFish() {
        Fish[][] fish = manage.getFishes();
        return Arrays.stream(fish)
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/add-animal", consumes = "text/plain")
    public String addAnimal(@RequestBody String input) {
        try {
            String[] parts = input.split(",");
            String type = parts[0];
            return switch (type) {
                case "Penguin" -> {
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    double height = Double.parseDouble(parts[3]);
                    yield manage.addPenguin(name, age, height);
                }
                case "Lion" -> {
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    double weight = Double.parseDouble(parts[3]);
                    String gender = parts[4];
                    yield manage.addPredator(name, age, weight, gender, "Lion");
                }
                case "Tiger" -> {
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    double weight = Double.parseDouble(parts[3]);
                    String gender = parts[4];
                    yield manage.addPredator(name, age, weight, gender, "Tiger");
                }
                case "AquariumFish" -> {
                    int age = Integer.parseInt(parts[1]);
                    double length = Double.parseDouble(parts[2]);
                    String pattern = parts[3];
                    String[] colors = Arrays.copyOfRange(parts, 4, parts.length);
                    yield manage.createFish(age, length, pattern, colors, "AquariumFish");
                }
                case "ClownFish" -> {
                    int age = Integer.parseInt(parts[1]);
                    double length = Double.parseDouble(parts[2]);
                    String[] colors = Arrays.copyOfRange(parts, 3, parts.length);
                    yield manage.createFish(age, length, null, colors, "ClownFish");
                }
                case "GoldFish" -> {
                    int age = Integer.parseInt(parts[1]);
                    double length = Double.parseDouble(parts[2]);
                    String[] colors = Arrays.copyOfRange(parts, 3, parts.length);
                    yield manage.createFish(age, length, null, colors, "GoldFish");
                }
                case "RandomFish" -> {
                    int amount = Integer.parseInt(parts[1]);
                    yield manage.createRandomFish(amount);
                }
                default -> "Invalid animal type.";
            };
        } catch (Exception e){
            return "Error: something went wrong.. " + e.getMessage() + ",  check your data request again ..";
        }
    }

    @GetMapping("/feedAll")
    public String feedAll() {
        return manage.feedAll();
    }

    @GetMapping("/soundAll")
    public String soundAll() {
        return manage.showAnimalsSounds();
    }

    @GetMapping("/dominantColors")
    public String dominantColors() {
        return manage.getDominantColors();
    }

}
