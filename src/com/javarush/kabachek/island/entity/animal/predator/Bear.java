package com.javarush.kabachek.island.entity.animal.predator;

import com.javarush.kabachek.island.entity.animal.Predator;

public class Bear extends Predator {

    public Bear() {
        super(500, 5, 2, 80, 2);
    }

    @Override
    public String getName() {
        return "Медведь";
    }

    @Override
    public String getIcon() {
        return "🐻";
    }
}
