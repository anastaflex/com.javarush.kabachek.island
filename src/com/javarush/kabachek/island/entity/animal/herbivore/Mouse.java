package com.javarush.kabachek.island.entity.animal.herbivore;

import com.javarush.kabachek.island.entity.animal.Herbivore;

public class Mouse extends Herbivore {

    public Mouse() {
        super(0.05, 500, 1, 0.01, 8);
    }

    @Override
    public String getName() {
        return "Мышь";
    }

    @Override
    public String getIcon() {
        return "🐁";
    }
}
