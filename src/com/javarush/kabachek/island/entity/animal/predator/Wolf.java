package com.javarush.kabachek.island.entity.animal.predator;

import com.javarush.kabachek.island.entity.animal.Predator;

public class Wolf extends Predator {

    public Wolf() {
        super(50, 30, 3, 8, 3);
    }

    @Override
    public String getName() {
        return "Волк";
    }

    @Override
    public String getIcon() {
        return "🐺";
    }
}
