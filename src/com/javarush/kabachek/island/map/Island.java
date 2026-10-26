package com.javarush.kabachek.island.map;

import com.javarush.kabachek.island.config.Config;

public class Island {

    private static final Island INSTANCE = new Island(Config.WIDTH, Config.HEIGHT);

    private final int width;
    private final int height;
    private final Location[][] locations;

    private Island(int width, int height) {
        this.width = width;
        this.height = height;
        locations = new Location[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                locations[y][x] = new Location(x, y);
            }
        }
    }

    public static Island getInstance() {
        return INSTANCE;
    }

    public Location getLocation(int x, int y) {
        return locations[y][x];
    }

    public boolean isInside(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Location[][] getLocations() {
        return locations;
    }
}
