package com.javarush.kabachek.island.entity.animal.herbivore;

import com.javarush.kabachek.island.entity.animal.Herbivore;

public class Boar extends Herbivore {

    public Boar() {
        super(400, 50, 2, 50, 4);
    }

    @Override
    public String getName() {
        return "Кабан";
    }

    @Override
    public String getIcon() {
        return "🐗";
    }
}
