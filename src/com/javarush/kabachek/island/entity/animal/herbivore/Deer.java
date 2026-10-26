package com.javarush.kabachek.island.entity.animal.herbivore;

import com.javarush.kabachek.island.entity.animal.Herbivore;

public class Deer extends Herbivore {

    public Deer() {
        super(300, 20, 4, 50, 1);
    }

    @Override
    public String getName() {
        return "Олень";
    }

    @Override
    public String getIcon() {
        return "🦌";
    }
}
