package com.javarush.kabachek.island.entity;

public abstract class Organism {

    private final double weight;
    private volatile boolean alive = true;

    protected Organism(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isAlive() {
        return alive;
    }

    public synchronized boolean die() {
        if (!alive) {
            return false;
        }
        alive = false;
        return true;
    }
}
