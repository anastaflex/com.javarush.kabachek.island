package com.javarush.kabachek.island.entity.animal;

public abstract class Herbivore extends Animal {

    protected Herbivore(double weight, int maxPerLocation, int speed, double foodNeeded, int maxChildren) {
        super(weight, maxPerLocation, speed, foodNeeded, maxChildren);
    }
}
