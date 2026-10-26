package com.javarush.kabachek.island.entity.animal.predator;

import com.javarush.kabachek.island.entity.animal.Predator;

public class Boa extends Predator {

    public Boa() {
        super(15, 30, 1, 3, 2);
    }

    @Override
    public String getName() {
        return "Удав";
    }

    @Override
    public String getIcon() {
        return "🐍";
    }
}
