package com.javarush.kabachek.island.entity.animal.herbivore;

import com.javarush.kabachek.island.entity.animal.Herbivore;

public class Horse extends Herbivore {

    public Horse() {
        super(400, 20, 4, 60, 1);
    }

    @Override
    public String getName() {
        return "Лошадь";
    }

    @Override
    public String getIcon() {
        return "🐎";
    }
}
