import com.github.ejektaflex.coldsnap.Helpers.KConfig

object GeneralConfig : KConfig("coldsnap.cfg") {
    // All crops that should die in the winter
    lateinit var fragileCrops: Array<String>
    // All crops that have their growth reset in the winter
    lateinit var partialCrops: Array<String>
    // Chance for a crop to stall growth in late fall
    var fallFrostChance: Double = 0.0
    // Chance for a crop to grow in general
    var baseGrowthChance: Double = 0.0

    private val CATEGORY_GENERAL = "general"

    init {
        initCropSettings()
    }

    private fun initCropSettings() {
        fragileCrops = this.config.getStringList(
                "fragileCrops", CATEGORY_GENERAL,
                // Default crops that should die in the winter
                arrayOf("harvestcraft:pamblackberrycrop", "harvestcraft:pamblueberrycrop", "harvestcraft:pamcandleberrycrop", "harvestcraft:pamraspberrycrop", "harvestcraft:pamstrawberrycrop", "harvestcraft:pamcactusfruitcrop", "harvestcraft:pamasparaguscrop", "harvestcraft:pambarleycrop", "harvestcraft:pamoatscrop", "harvestcraft:pamryecrop", "harvestcraft:pamcorncrop", "harvestcraft:pambambooshootcrop"),
                "A list of crops that should die out in the winter"
        )

        partialCrops = this.config.getStringList(
                "partialCrops", CATEGORY_GENERAL,
                // Default crops that should die in the winter
                arrayOf("rustic:chili_crop", "rustic:wildberry_bush"),
                "A list of crops that should not grow in winter, but not die"
        )

        fallFrostChance = this.config.get(
                CATEGORY_GENERAL,
                "fallFrostChance",
                0.5,
                "Percent chance [0-1] of a crop not growing in late autumn. Used to simulate the slowing of crop growth."
        ).double

        baseGrowthChance = this.config.get(
                CATEGORY_GENERAL,
                "baseGrowthChance",
                1.0,
                "Random base chance [0-1] of a crop growing. E.g. 0.5 would make crops grow half as fast, on average."
        ).double
    }

}
