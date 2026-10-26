package com.javarush.kabachek.island.config;

public final class Config {

    private Config() {
    }

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    public static final long DAY_DURATION_MS = 1000;

    public static final int MAX_DAYS = 30;

    public static final double START_FILL = 0.5;

    public static final int MAX_PLANTS = 200;
    public static final double PLANT_WEIGHT = 1;
    public static final double PLANT_GROWTH = 0.3;

    public static final double HUNGER_PER_DAY = 0.25;

    public static final int REPRODUCE_CHANCE = 30;

    public static final int HUNT_ATTEMPTS = 5;

    public static final int THREADS = 4;
}
