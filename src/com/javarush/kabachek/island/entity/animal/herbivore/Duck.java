package com.javarush.kabachek.island.entity.animal.herbivore;

import com.javarush.kabachek.island.entity.animal.Herbivore;

public class Duck extends Herbivore {

    public Duck() {
        super(1, 200, 4, 0.15, 5);
    }

    @Override
    public String getName() {
        return "Утка";
    }

    @Override
    public String getIcon() {
        return "🦆";
    }
}
