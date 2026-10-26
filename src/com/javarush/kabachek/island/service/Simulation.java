package com.javarush.kabachek.island.service;

import com.javarush.kabachek.island.config.Config;
import com.javarush.kabachek.island.entity.animal.Animal;
import com.javarush.kabachek.island.entity.animal.herbivore.*;
import com.javarush.kabachek.island.entity.animal.predator.*;
import com.javarush.kabachek.island.map.Island;
import com.javarush.kabachek.island.map.Location;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Simulation {

    private static final List<Supplier<Animal>> SPECIES = List.of(
            Wolf::new, Boa::new, Fox::new, Bear::new, Eagle::new,
            Horse::new, Deer::new, Rabbit::new, Mouse::new, Goat::new,
            Sheep::new, Boar::new, Buffalo::new, Duck::new, Caterpillar::new
    );

    private final Island island = Island.getInstance();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ExecutorService workers = Executors.newFixedThreadPool(Config.THREADS);
    private int day = 0;

    public void start() {
        populate();
        printStatistics();
        scheduler.scheduleAtFixedRate(this::nextDay, Config.DAY_DURATION_MS,
                Config.DAY_DURATION_MS, TimeUnit.MILLISECONDS);
    }

    private void populate() {
        for (Location[] row : island.getLocations()) {
            for (Location location : row) {
                for (Supplier<Animal> species : SPECIES) {
                    int count = (int) (species.get().getMaxPerLocation() * Config.START_FILL);
                    for (int i = 0; i < count; i++) {
                        location.addAnimal(species.get());
                    }
                }
                location.growPlants(Config.MAX_PLANTS);
            }
        }
    }

    private void nextDay() {
        try {
            day++;
            liveDay();
            growPlants();
            int animalsLeft = printStatistics();
            if (day >= Config.MAX_DAYS || animalsLeft == 0) {
                stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
    }

    private void growPlants() {
        int amount = (int) (Config.MAX_PLANTS * Config.PLANT_GROWTH);
        for (Location[] row : island.getLocations()) {
            for (Location location : row) {
                location.growPlants(amount);
            }
        }
    }

    private void liveDay() throws InterruptedException {
        List<Callable<Void>> tasks = new ArrayList<>();
        for (Location[] row : island.getLocations()) {
            for (Location location : row) {
                tasks.add(() -> {
                    liveLocation(location);
                    return null;
                });
            }
        }
        workers.invokeAll(tasks);

        for (Location[] row : island.getLocations()) {
            for (Location location : row) {
                location.removeDead();
            }
        }
    }

    private void liveLocation(Location location) {
        for (Animal animal : location.getAnimals()) {
            if (!animal.isAlive()) {
                continue;
            }
            animal.eat(location);
            animal.reproduce(location);
            animal.getHungry();
            if (animal.isAlive()) {
                animal.move(location);
            }
        }
    }

    private int printStatistics() {
        Map<String, Integer> stats = new LinkedHashMap<>();
        for (Supplier<Animal> species : SPECIES) {
            Animal animal = species.get();
            stats.put(animal.getIcon() + " " + animal.getName(), 0);
        }

        int total = 0;
        int plants = 0;
        for (Location[] row : island.getLocations()) {
            for (Location location : row) {
                plants += location.getPlantsCount();
                for (Animal animal : location.getAnimals()) {
                    if (animal.isAlive()) {
                        stats.merge(animal.getIcon() + " " + animal.getName(), 1, Integer::sum);
                        total++;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n========== День ").append(day).append(" ==========\n");
        int i = 0;
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            sb.append(String.format("%-14s %7d   ", entry.getKey(), entry.getValue()));
            if (++i % 5 == 0) {
                sb.append('\n');
            }
        }
        sb.append("🌿 Растения: ").append(plants)
                .append("   Всего животных: ").append(total);
        System.out.println(sb);
        return total;
    }

    private void stop() {
        System.out.println("\nСимуляция завершена на " + day + " дне");
        workers.shutdown();
        scheduler.shutdown();
    }
}
