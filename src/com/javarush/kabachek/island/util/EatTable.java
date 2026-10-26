package com.javarush.kabachek.island.util;

import com.javarush.kabachek.island.entity.animal.herbivore.*;
import com.javarush.kabachek.island.entity.animal.predator.*;
import com.javarush.kabachek.island.entity.plant.Plant;

import java.util.HashMap;
import java.util.Map;

public final class EatTable {

    private static final Map<Class<?>, Map<Class<?>, Integer>> TABLE = new HashMap<>();

    static {
        put(Wolf.class, Horse.class, 10);
        put(Wolf.class, Deer.class, 15);
        put(Wolf.class, Rabbit.class, 60);
        put(Wolf.class, Mouse.class, 80);
        put(Wolf.class, Goat.class, 60);
        put(Wolf.class, Sheep.class, 70);
        put(Wolf.class, Boar.class, 15);
        put(Wolf.class, Buffalo.class, 10);
        put(Wolf.class, Duck.class, 40);

        put(Boa.class, Fox.class, 15);
        put(Boa.class, Rabbit.class, 20);
        put(Boa.class, Mouse.class, 40);
        put(Boa.class, Duck.class, 10);

        put(Fox.class, Rabbit.class, 70);
        put(Fox.class, Mouse.class, 90);
        put(Fox.class, Duck.class, 60);
        put(Fox.class, Caterpillar.class, 40);

        put(Bear.class, Boa.class, 80);
        put(Bear.class, Horse.class, 40);
        put(Bear.class, Deer.class, 80);
        put(Bear.class, Rabbit.class, 80);
        put(Bear.class, Mouse.class, 90);
        put(Bear.class, Goat.class, 70);
        put(Bear.class, Sheep.class, 70);
        put(Bear.class, Boar.class, 50);
        put(Bear.class, Buffalo.class, 20);
        put(Bear.class, Duck.class, 10);

        put(Eagle.class, Fox.class, 10);
        put(Eagle.class, Rabbit.class, 90);
        put(Eagle.class, Mouse.class, 90);
        put(Eagle.class, Duck.class, 80);

        put(Horse.class, Plant.class, 100);
        put(Deer.class, Plant.class, 100);
        put(Rabbit.class, Plant.class, 100);
        put(Mouse.class, Caterpillar.class, 90);
        put(Mouse.class, Plant.class, 100);
        put(Goat.class, Plant.class, 100);
        put(Sheep.class, Plant.class, 100);
        put(Boar.class, Mouse.class, 50);
        put(Boar.class, Caterpillar.class, 90);
        put(Boar.class, Plant.class, 100);
        put(Buffalo.class, Plant.class, 100);
        put(Duck.class, Caterpillar.class, 90);
        put(Duck.class, Plant.class, 100);
        put(Caterpillar.class, Plant.class, 100);
    }

    private EatTable() {
    }

    private static void put(Class<?> who, Class<?> food, int chance) {
        TABLE.computeIfAbsent(who, k -> new HashMap<>()).put(food, chance);
    }

    public static int getChance(Class<?> who, Class<?> food) {
        Map<Class<?>, Integer> foods = TABLE.get(who);
        if (foods == null) {
            return 0;
        }
        return foods.getOrDefault(food, 0);
    }
}
