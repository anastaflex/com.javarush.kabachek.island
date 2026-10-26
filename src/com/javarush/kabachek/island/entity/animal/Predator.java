package com.javarush.kabachek.island.entity.animal;

public abstract class Predator extends Animal {

    protected Predator(double weight, int maxPerLocation, int speed, double foodNeeded, int maxChildren) {
        super(weight, maxPerLocation, speed, foodNeeded, maxChildren);
    }
}
