package com.javarush.kabachek.island.entity.animal.herbivore;

import com.javarush.kabachek.island.entity.animal.Herbivore;

public class Sheep extends Herbivore {

    public Sheep() {
        super(70, 140, 3, 15, 2);
    }

    @Override
    public String getName() {
        return "Овца";
    }

    @Override
    public String getIcon() {
        return "🐑";
    }
}
