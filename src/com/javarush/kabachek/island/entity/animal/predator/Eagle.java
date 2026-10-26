package com.javarush.kabachek.island.entity.animal.predator;

import com.javarush.kabachek.island.entity.animal.Predator;

public class Eagle extends Predator {

    public Eagle() {
        super(6, 20, 3, 1, 2);
    }

    @Override
    public String getName() {
        return "Орел";
    }

    @Override
    public String getIcon() {
        return "🦅";
    }
}
