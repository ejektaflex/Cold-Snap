package com.example.examplemod;

import net.minecraftforge.common.config.Configuration;

class ConfigHandler {

    private static final String CATEGORY_GENERAL = "general";

    private Configuration config;
    // All crops that should die in the winter
    public String[] fragileCrops;

    ConfigHandler(Configuration c) {
        this.config = c;
        initCropSettings();
    }

    private void initCropSettings() {
        this.config.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
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
