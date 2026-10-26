package com.javarush.kabachek.island.entity.animal.herbivore;

import com.javarush.kabachek.island.entity.animal.Herbivore;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super(0.01, 1000, 0, 0, 10);
    }

    @Override
    public String getName() {
        return "Гусеница";
    }

    @Override
    public String getIcon() {
        return "🐛";
    }
}
