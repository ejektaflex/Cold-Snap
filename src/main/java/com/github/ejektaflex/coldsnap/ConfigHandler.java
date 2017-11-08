package com.github.ejektaflex.coldsnap;

import net.minecraftforge.common.config.Configuration;

class ConfigHandler {

    private static final String CATEGORY_GENERAL = "general";

    private Configuration config;
    // All crops that should die in the winter
    public String[] fragileCrops;
    // All crops that have their growth reset in the winter
    public String[] partialCrops;
    // Chance for a crop to stall growth in late fall
    public double fallFrostChance;
    // Chance for a crop to grow in general
    public double baseGrowthChance;

    ConfigHandler(Configuration c) {
        this.config = c;
        initCropSettings();
    }

    private void initCropSettings() {
        fragileCrops = this.config.getStringList(
                "fragileCrops", CATEGORY_GENERAL,
                // Default crops that should die in the winter
                new String[]{
                        "harvestcraft:pamblackberrycrop",
                        "harvestcraft:pamblueberrycrop",
                        "harvestcraft:pamcandleberrycrop",
                        "harvestcraft:pamraspberrycrop",
                        "harvestcraft:pamstrawberrycrop",
                        "harvestcraft:pamcactusfruitcrop",
                        "harvestcraft:pamasparaguscrop",
                        "harvestcraft:pambarleycrop",
                        "harvestcraft:pamoatscrop",
                        "harvestcraft:pamryecrop",
                        "harvestcraft:pamcorncrop",
                        "harvestcraft:pambambooshootcrop"
                },
                "A list of crops that should die out in the winter"
        );

        partialCrops = this.config.getStringList(
                "partialCrops", CATEGORY_GENERAL,
                // Default crops that should die in the winter
                new String[]{
                        "rustic:chili_crop",
                        "rustic:wildberry_bush"
                },
                "A list of crops that should not grow in winter, but not die"
        );

        fallFrostChance = this.config.get(
                CATEGORY_GENERAL,
                "fallFrostChance",
                0.5,
                "Percent chance [0-1] of a crop not growing in late autumn. Used to simulate the slowing of crop growth."
        ).getDouble();

        baseGrowthChance = this.config.get(
                CATEGORY_GENERAL,
                "baseGrowthChance",
                1.0,
                "Random base chance [0-1] of a crop growing. E.g. 0.5 would make crops grow half as fast, on average."
        ).getDouble();

    }

    public void load() {
        for (String itemName : fragileCrops) {
            System.out.println("Item name: " + itemName);
        }
    }

    public void save() {
        this.config.save();
    }
}
