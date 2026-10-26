package com.javarush.kabachek.island.entity.animal.herbivore;

import com.javarush.kabachek.island.entity.animal.Herbivore;

public class Goat extends Herbivore {

    public Goat() {
        super(60, 140, 3, 10, 2);
    }

    @Override
    public String getName() {
        return "Коза";
    }

    @Override
    public String getIcon() {
        return "🐐";
    }
}
