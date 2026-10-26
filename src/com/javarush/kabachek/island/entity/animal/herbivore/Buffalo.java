package com.javarush.kabachek.island.entity.animal.herbivore;

import com.javarush.kabachek.island.entity.animal.Herbivore;

public class Buffalo extends Herbivore {

    public Buffalo() {
        super(700, 10, 3, 100, 1);
    }

    @Override
    public String getName() {
        return "Буйвол";
    }

    @Override
    public String getIcon() {
        return "🐃";
    }
}
