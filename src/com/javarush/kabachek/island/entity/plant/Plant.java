package com.javarush.kabachek.island.entity.plant;

import com.javarush.kabachek.island.config.Config;
import com.javarush.kabachek.island.entity.Organism;

public class Plant extends Organism {

    public Plant() {
        super(Config.PLANT_WEIGHT);
    }
}
