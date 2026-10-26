package com.javarush.kabachek.island.entity.animal.predator;

import com.javarush.kabachek.island.entity.animal.Predator;

public class Fox extends Predator {

    public Fox() {
        super(8, 30, 2, 2, 4);
    }

    @Override
    public String getName() {
        return "Лиса";
    }

    @Override
    public String getIcon() {
        return "🦊";
    }
}
