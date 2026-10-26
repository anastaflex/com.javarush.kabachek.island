package com.javarush.kabachek.island.map;

import com.javarush.kabachek.island.config.Config;
import com.javarush.kabachek.island.entity.animal.Animal;
import com.javarush.kabachek.island.entity.plant.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Location {

    private final int x;
    private final int y;
    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();
    private final Map<Class<? extends Animal>, Integer> counts = new HashMap<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized boolean addAnimal(Animal animal) {
        int count = counts.getOrDefault(animal.getClass(), 0);
        if (count >= animal.getMaxPerLocation()) {
            return false;
        }
        animals.add(animal);
        counts.put(animal.getClass(), count + 1);
        return true;
    }

    public synchronized void removeAnimal(Animal animal) {
        if (animals.remove(animal)) {
            counts.merge(animal.getClass(), -1, Integer::sum);
        }
    }

    public synchronized int countOf(Class<? extends Animal> type) {
        return counts.getOrDefault(type, 0);
    }

    public synchronized Animal getRandomAnimal() {
        if (animals.isEmpty()) {
            return null;
        }
        return animals.get(ThreadLocalRandom.current().nextInt(animals.size()));
    }

    public synchronized List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public synchronized Plant takePlant() {
        if (plants.isEmpty()) {
            return null;
        }
        return plants.remove(plants.size() - 1);
    }

    public synchronized void growPlants(int amount) {
        for (int i = 0; i < amount && plants.size() < Config.MAX_PLANTS; i++) {
            plants.add(new Plant());
        }
    }

    public synchronized int getPlantsCount() {
        return plants.size();
    }

    public synchronized void removeDead() {
        animals.removeIf(animal -> !animal.isAlive());
        counts.clear();
        for (Animal animal : animals) {
            counts.merge(animal.getClass(), 1, Integer::sum);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
