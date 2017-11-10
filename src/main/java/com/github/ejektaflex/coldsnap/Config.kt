package com.github.ejektaflex.coldsnap

import com.github.ejektaflex.coldsnap.Helpers.KConfig
import net.minecraftforge.common.config.Config

@Config(modid = ModInfo.MODID)
object Config : KConfig() {

    @Config.Comment(
            "Percent chance [0-1] of a crop not growing in late autumn. " +
            "Used to simulate the slowing of crop growth"
    )
    @JvmStatic var fallFrostChance = 0.5

    @Config.Comment(
            "Random base chance [0-1] of a crop growing. E.g. 0.5 would " +
            "make crops grow half as fast, on average."
    )
    @JvmStatic var baseGrowthChance = 1.0

    @Config.Comment("A list of crops that should die out in the winter")
    @JvmStatic var fragileCrops = arrayOf(
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
    )

    @Config.Comment("A list of crops that should not die, but be reset in the winter")
    @JvmStatic var partialCrops = arrayOf(
            "rustic:chili_crop",
            "rustic:wildberry_bush"
    )

}