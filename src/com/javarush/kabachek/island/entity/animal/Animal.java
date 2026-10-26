package com.javarush.kabachek.island.entity.animal;

import com.javarush.kabachek.island.config.Config;
import com.javarush.kabachek.island.entity.Organism;
import com.javarush.kabachek.island.entity.plant.Plant;
import com.javarush.kabachek.island.map.Island;
import com.javarush.kabachek.island.map.Location;
import com.javarush.kabachek.island.util.EatTable;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism {

    private final int maxPerLocation;
    private final int speed;
    private final double foodNeeded;
    private final int maxChildren;

    private double satiety;

    protected Animal(double weight, int maxPerLocation, int speed, double foodNeeded, int maxChildren) {
        super(weight);
        this.maxPerLocation = maxPerLocation;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.maxChildren = maxChildren;
        this.satiety = foodNeeded / 2;
    }

    public abstract String getName();

    public abstract String getIcon();

    public void eat(Location location) {
        if (foodNeeded == 0) {
            return;
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < Config.HUNT_ATTEMPTS && isHungry(); i++) {
            Animal victim = location.getRandomAnimal();
            if (victim == null || victim == this || !victim.isAlive()) {
                continue;
            }
            int chance = EatTable.getChance(getClass(), victim.getClass());
            if (chance > 0 && random.nextInt(100) < chance && victim.die()) {
                addFood(victim.getWeight());
            }
        }

        if (EatTable.getChance(getClass(), Plant.class) > 0) {
            while (isHungry()) {
                Plant plant = location.takePlant();
                if (plant == null) {
                    break;
                }
                addFood(plant.getWeight());
            }
        }
    }

    public void reproduce(Location location) {
        if (location.countOf(getClass()) < 2) {
            return;
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();
        if (random.nextInt(100) >= Config.REPRODUCE_CHANCE) {
            return;
        }
        int children = random.nextInt(maxChildren) + 1;
        for (int i = 0; i < children; i++) {
            if (!location.addAnimal(createChild())) {
                break;
            }
        }
    }

    public void move(Location from) {
        if (speed == 0) {
            return;
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Island island = Island.getInstance();
        int x = from.getX();
        int y = from.getY();
        int steps = random.nextInt(speed + 1);

        for (int i = 0; i < steps; i++) {
            int direction = random.nextInt(4);
            int newX = x;
            int newY = y;
            switch (direction) {
                case 0 -> newY--;
                case 1 -> newY++;
                case 2 -> newX--;
                default -> newX++;
            }
            if (island.isInside(newX, newY)) {
                x = newX;
                y = newY;
            }
        }

        Location to = island.getLocation(x, y);
        if (to != from && to.addAnimal(this)) {
            from.removeAnimal(this);
        }
    }

    public void getHungry() {
        if (foodNeeded == 0) {
            return;
        }
        satiety -= foodNeeded * Config.HUNGER_PER_DAY;
        if (satiety <= 0) {
            die();
        }
    }

    private boolean isHungry() {
        return satiety < foodNeeded;
    }

    private void addFood(double food) {
        satiety = Math.min(foodNeeded, satiety + food);
    }

    private Animal createChild() {
        try {
            return getClass().getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Не удалось создать детеныша " + getName(), e);
        }
    }

    public int getMaxPerLocation() {
        return maxPerLocation;
    }
}
