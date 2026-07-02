package com.example.zoo.ass3.models;

import com.example.zoo.ass3.exceptions.*;
import com.example.zoo.ass3.general.Address;
import com.example.zoo.ass3.general.FoodSummary;
import com.example.zoo.ass3.general.enums.*;

import java.io.Serializable;
import java.util.*;

import static com.example.zoo.ass3.general.DataUtils.*;

public class Zoo implements Serializable {
    private final String name;
    private final Address address;
    private final Map<String, List<Animal>> animals;
    private final VeterinaryClinic<Animal> veterinaryClinic;

    public Zoo(String name, Address address) {
        this.name = name;
        this.address = address;
        animals = new HashMap<>();
        animals.put(Predator.class.getSimpleName(), new ArrayList<>());
        animals.put(Fish.class.getSimpleName(), new ArrayList<>());
        animals.put(Penguin.class.getSimpleName(), new ArrayList<>());
        veterinaryClinic = new VeterinaryClinic<>();
    }

    public void addPenguin(String name, int age, double height) throws GeneralException {
        if (!isValidMinimumPhysicalSize(age) || !isValidMinimumPhysicalSize(height)) {
            throw new PenguinHeightException();
        }
        addPenguin(new Penguin(name, age, height));
    }

    public void addPenguin(Penguin penguin) throws PenguinHeightException {
        // first leader penguin
        Penguin leader = getPenguinsLeader();
        if (leader == null) {
            penguin.setLeader(true);
            animals.put(Penguin.class.getSimpleName(), new ArrayList<>(List.of(penguin)));
            return;
        }
        // check valid height against leader penguin
        if (penguin.getHeight() > leader.getHeight()) {
            throw new PenguinHeightException();
        }
        Penguin temp = leader;
        Penguin prev = null;
        while (temp != null && temp.getHeight() > penguin.getHeight()) {
            prev = temp;
            temp = temp.getNext();
        }
        if (temp == null) {
            prev.setNext(penguin);
        } else if (prev != null) {
            prev.setNext(penguin);
            penguin.setNext(temp);
        }
    }

    private Penguin getPenguinsLeader() {
        List<Animal> list = animals.get(Penguin.class.getSimpleName());
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (Penguin) animals.get(Penguin.class.getSimpleName()).get(0);
    }

    public void addPredator(String name, int age, double weight, String gender, PredatorsTypes type) throws GeneralException {
        if (!isValidMinimumPhysicalSize(age)) {
            throw new AgeException();
        }
        if (!isValidMinimumPhysicalSize(weight)) {
            throw new WeightException();
        }
        Gender g = getGender(gender);
        if (g == null) {
            throw new GenderException();
        }
        switch (type) {
            case Lion -> addPredator(new Lion(name, age, weight, g));
            case Tiger -> addPredator(new Tiger(name, age, weight, g));
        }
    }

    public void addPredator(Predator predator) {
        animals.get(Predator.class.getSimpleName()).add(predator);
    }

    public void addFish(int age, double length, String patternStr, String[] colorsStr, FishTypes type) throws GeneralException {
        Color[] colors;
        switch (type) {
            case AquariumFish -> {
                Pattern pattern = getPattern(patternStr);
                if (pattern == null) {
                    throw new PatternException();
                }
                colors = createColorArr(colorsStr);
                animals.get(Fish.class.getSimpleName()).add(new AquariumFish(age, length, pattern, colors));
            }
            case ClownFish -> {
                colors = createColorArr(colorsStr);
                animals.get(Fish.class.getSimpleName()).add(new ClownFish(age, length, colors));
            }
            case GoldFish -> {
                colors = createColorArr(colorsStr);
                animals.get(Fish.class.getSimpleName()).add(new GoldFish(age, length, colors[0]));
            }
        }
    }

    private Color[] createColorArr(String[] colorsStr) throws ColorNotExistException {
        if (colorsStr == null || colorsStr.length == 0) {
            throw new ColorNotExistException();
        }
        Color[] colors = new Color[colorsStr.length];
        Color temp;
        for (int i = 0; i < colorsStr.length; i++) {
            temp = getColor(colorsStr[i]);
            if (temp == null) {
                throw new ColorNotExistException();
            }
            colors[i] = temp;
        }
        return colors;
    }

    public List<FoodSummary> feedAnimals() {
        Map<String, FoodSummary> totals = new HashMap<>();
        FoodSummary temp;
        for (List<Animal> list : animals.values()) {
            for (Animal animal : list) {
                String type = animal.getClass().getSimpleName();
                double value = animal.feedAnimal();
                temp = totals.get(type);
                if (temp == null) {
                    temp = new FoodSummary(type, 0.0, animal.getUnit());
                }
                temp.setAmount(set2Digits(temp.getAmount() + value));
                totals.put(type, temp);
            }
        }
        return new ArrayList<>(totals.values());
    }


    public String showAnimalsSounds() {
        StringBuilder sb = new StringBuilder();
        for (List<Animal> list : animals.values()) {
            for (Animal animal : list) {
                sb.append(animal.makeNoise());
            }
        }
        return sb.toString();
    }

    private int getNumOfPenguins(Penguin penguin) {
        if (penguin == null) {
            return 0;
        }
        return 1 + getNumOfPenguins(penguin.getNext());
    }

    public List<String> increasingAgeOneYear() {
        List<String> results = new ArrayList<>();
        results.add("All Animals was added on year..");
        for (List<Animal> list : animals.values()) {
            Iterator<Animal> it = list.iterator();
            while (it.hasNext()) {
                Animal animal = it.next();
                if (!animal.ageOneYear()) {
                    results.add(animal + " was deleted");
                    it.remove();
                }
            }
        }
        return results;
    }

    public void init() throws GeneralException {
        Penguin penguin = new Penguin("Doris", 12, 200f);
        addPenguin(penguin);
        penguin = new Penguin("Shalom", 2, 51.5);
        addPenguin(penguin);
        penguin = new Penguin("Bar", 10, 162.8);
        addPenguin(penguin);
        veterinaryClinic.addAnimal(penguin);
        veterinaryClinic.addTreatment(penguin, new MedicalTreatment("Antibiotic treatment for infection"));
        Lion lion = new Lion("Barak", 5, 80, Gender.MALE);
        addPredator(lion);
        lion = new Lion("Dany", 12, 180, Gender.MALE);
        addPredator(lion);
        lion = new Lion("Ana", 3, 50, Gender.FEMALE);
        addPredator(lion);
        veterinaryClinic.addAnimal(lion);
        veterinaryClinic.addTreatment(lion, new MedicalTreatment("Dental check-up"));
        lion = new Lion("Tami", 15, 200, Gender.FEMALE);
        addPredator(lion);
        Tiger tiger = new Tiger("Dana", 5, 80, Gender.FEMALE);
        addPredator(tiger);
        tiger = new Tiger("Ringo", 9, 120, Gender.MALE);
        veterinaryClinic.addAnimal(tiger);
        veterinaryClinic.addTreatment(tiger, new MedicalTreatment("Routine health examination"));
        addPredator(tiger);
        addFish(10);

    }

    private void addFish(int amount) throws GeneralException {
        Random r = new Random();
        for (int i = 0; i < amount; i++) {
            createAqFish(r);
        }
    }

    public String createFish(int amount) throws GeneralException {
        Random r = new Random();
        FishTypes fishTypes = FishTypes.values()[r.nextInt(FishTypes.values().length)];
        switch (fishTypes) {
            case AquariumFish -> {
                for (int i = 0; i < amount; i++) {
                    createAqFish(r);
                }
            }
            case ClownFish -> {
                for (int i = 0; i < amount; i++) {
                    createClownFish(r);
                }
            }
            case GoldFish -> {
                for (int i = 0; i < amount; i++) {
                    createGoldFish(r);
                }
            }
        }
        return amount + " fish created successfully";
    }

    private void createAqFish(Random r) throws GeneralException {
        double length;
        Color[] colors;
        int numOfColors;
        int age;
        Pattern pattern;
        int patternSize = Pattern.values().length, colorSize = Color.values().length;
        age = r.nextInt(15) + 1;
        length = set2Digits(r.nextDouble(4) + 1);
        pattern = Pattern.values()[r.nextInt(patternSize)];
        numOfColors = r.nextInt(4) + 1;
        colors = new Color[numOfColors];
        Color color;
        boolean colorExist;
        for (int j = 0; j < numOfColors; j++) {
            do {
                color = Color.values()[r.nextInt(colorSize)];
                colorExist = colorExist(colors, j, color);
            } while (colorExist);
            colors[j] = color;
        }
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish == null) {
            fish = new ArrayList<>();
        }
        fish.add(new AquariumFish(age, length, pattern, colors));
        animals.put(Fish.class.getSimpleName(), fish);
    }

    private void createClownFish(Random r) throws GeneralException {
        double length;
        Color[] colors;
        int numOfColors;
        int age;
        age = r.nextInt(15) + 1;
        length = set2Digits(r.nextDouble(4) + 1);
        numOfColors = r.nextInt(2) + 1;
        colors = new Color[numOfColors];
        Color color;
        boolean colorExist;
        for (int j = 0; j < numOfColors; j++) {
            do {
                color = ClownFish.AVAILABLE_COLORS[r.nextInt(ClownFish.AVAILABLE_COLORS.length)];
                colorExist = colorExist(colors, j, color);
            } while (colorExist);
            colors[j] = color;
        }
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish == null) {
            fish = new ArrayList<>();
        }
        fish.add(new ClownFish(age, length, colors));
        animals.put(Fish.class.getSimpleName(), fish);
    }

    private void createGoldFish(Random r) throws GeneralException {
        double length;
        int age;
        age = r.nextInt(15) + 1;
        length = set2Digits(r.nextDouble(4) + 1);
        Color color = GoldFish.AVAILABLE_COLORS[r.nextInt(GoldFish.AVAILABLE_COLORS.length)];
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish == null) {
            fish = new ArrayList<>();
        }
        fish.add(new GoldFish(age, length, color));
        animals.put(Fish.class.getSimpleName(), fish);
    }

    public String getZooTitle() {
        return name + ", " + address;
    }

    public int[] getPredatorSummary() {
        int[] numOfPredators = new int[PredatorsTypes.values().length];
        List<Animal> predators = animals.get(Predator.class.getSimpleName());
        if (predators != null && !predators.isEmpty()) {
            for (Animal animal : predators) {
                numOfPredators[PredatorsTypes.valueOf(animal.getClass().getSimpleName()).ordinal()]++;
            }
        }
        return numOfPredators;
    }

    public int getPenguinAmount() {
        Penguin leader = getPenguinsLeader();
        if (leader == null) {
            return 0;
        }
        return getNumOfPenguins(leader);
    }

    public int[] getFishSummary() {
        int[] numOfFish = new int[FishTypes.values().length];
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish != null && !fish.isEmpty()) {
            for (Animal animal : fish) {
                numOfFish[FishTypes.valueOf(animal.getClass().getSimpleName()).ordinal()]++;
            }
        }
        return numOfFish;
    }

    public Map<PredatorsTypes, List<Predator>> getPredators() {
        Map<PredatorsTypes, List<Predator>> predatorsMap = new HashMap<>();
        List<Animal> predators = animals.get(Predator.class.getSimpleName());
        if (predators != null && !predators.isEmpty()) {
            for (Animal animal : predators) {
                PredatorsTypes type = PredatorsTypes.valueOf(animal.getClass().getSimpleName());
                predatorsMap.computeIfAbsent(type, k -> new ArrayList<>());
                predatorsMap.get(type).add((Predator) animal);
                predatorsMap.put(type, predatorsMap.get(type));
            }
        }
        return predatorsMap;
    }

    public Map<FishTypes, List<Fish>> getFish() {
        Map<FishTypes, List<Fish>> fishMap = new HashMap<>();
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish != null && !fish.isEmpty()) {
            for (Animal animal : fish) {
                FishTypes type = FishTypes.valueOf(animal.getClass().getSimpleName());
                fishMap.computeIfAbsent(type, k -> new ArrayList<>());
                fishMap.get(type).add((Fish) animal);
                fishMap.put(type, fishMap.get(type));
            }
        }
        return fishMap;
    }

    public String getDominantColors() {
        int[] dominantColors = new int[Color.values().length];
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish != null && !fish.isEmpty()) {
            for (Animal animal : fish) {
                updateDominantColors(dominantColors, ((Fish) animal).getColors());
            }
        }
        Color[] colors = Color.values();
        int[] maxIndex = {-1, -1};
        updateMaxIndex(dominantColors, maxIndex);
        String res = "";
        if (maxIndex[0] != -1) {
            res += colors[maxIndex[0]];
            if (maxIndex[1] != -1) {
                res += "," + colors[maxIndex[1]];
            }
        }
        return res;
    }

    private void updateMaxIndex(int[] dominantColors, int[] maxIndex) {
        int max1 = -1, max2 = -1;
        for (int i = 0; i < dominantColors.length; i++) {
            if (dominantColors[i] > max1) {
                maxIndex[1] = maxIndex[0];
                maxIndex[0] = i;
                max2 = max1;
                max1 = dominantColors[i];
            } else if (dominantColors[i] > max2) {
                maxIndex[1] = i;
                max2 = dominantColors[i];
            }
        }
    }

    private void updateDominantColors(int[] dominantColors, Color[] colors) {
        for (Color color : colors) {
            dominantColors[color.ordinal()]++;
        }
    }

    public List<Penguin> getPenguins() throws GeneralException {
        List<Penguin> penguins = new ArrayList<>();
        Penguin leader = getPenguinsLeader();
        if (leader == null) {
            return penguins;
        }
        penguins.add(new Penguin(leader));
        Penguin temp = leader.getNext();
        while (temp != null) {
            penguins.add(new Penguin(temp));
            temp = temp.getNext();
        }
        return penguins;
    }

    public Map<String, Object> veterinarySummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalSick", veterinaryClinic.getAllAnimals().size());
        Map<String, Integer> byType = new HashMap<>();
        String animalClassName;
        for (Object animal : veterinaryClinic.getAllAnimals()) {
            animalClassName = animal.getClass().getSimpleName();
            if (byType.containsKey(animalClassName)) {
                byType.put(animalClassName, byType.get(animalClassName) + 1);
            } else {
                byType.put(animalClassName, 1);
            }
        }
        summary.put("byType", byType);
        return summary;
    }

    public Map<Animal, List<MedicalTreatment>> getVeterinaryClinic() {
        return veterinaryClinic.getRecords();
    }
}
