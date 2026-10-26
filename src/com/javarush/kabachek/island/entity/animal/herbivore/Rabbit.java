package com.javarush.kabachek.island.entity.animal.herbivore;

import com.javarush.kabachek.island.entity.animal.Herbivore;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super(2, 150, 2, 0.45, 6);
    }

    @Override
    public String getName() {
        return "Кролик";
    }

    @Override
    public String getIcon() {
        return "🐇";
    }
}
