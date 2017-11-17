import com.github.ejektaflex.coldsnap.KConfig

open class ConfigHandler(folder: String) : KConfig(folder, "coldsnap.cfg") {
    // All crops that should die in the winter
    open lateinit var fragileCrops: Array<String>
    // All crops that have their growth reset in the winter
    open lateinit var partialCrops: Array<String>
    // Chance for a crop to stall growth in late fall
    open var fallFrostChance: Double = 0.0
    // Chance for a crop to grow in general
    open var baseGrowthChance: Double = 0.0

    // Crops for Pam's Harvestcraft compat
    private var pamCrops = arrayOf(
            "blackberry",
            "blueberry",
            "candleberry",
            "raspberry",
            "strawberry",
            "cactusfruit",
            "asparagus",
            "barley",
            "oats",
            "rye",
            "corn",
            "bambooshoot",
            "cantaloupe",
            "cucumber",
            "wintersquash",
            "zucchini",
            "beet",
            "onion",
            "parsnip",
            "peanut",
            "radish",
            "rutabega",
            "sweetpotato",
            "turnip",
            "rhubarb",
            "celery",
            "garlic",
            "ginger",
            "spiceleaf",
            "tealeaf",
            "coffeebean",
            "mustardseeds",
            "broccoli",
            "cauliflower",
            "leek",
            "lettuce",
            "scallion",
            "artichoke",
            "brusselsprout",
            "cabbage",
            "spinach",
            "whitemushroom",
            "bean",
            "soybean",
            "bellpepper",
            "chilipepper",
            "eggplant",
            "okra",
            "peas",
            "tomato",
            "cotton",
            "pineapple",
            "grape",
            "kiwi",
            "cranberry",
            "rice",
            "seaweed",
            "curryleaf",
            "sesameseeds",
            "waterchestnut"
    )

    private val defaultCrops = pamCrops.map { "harvestcraft:pam${it}crop" }

    init {
        initCropSettings()
    }

    companion object {
        private val CATEGORY_GENERAL = "general"
    }

    private fun initCropSettings() {
        fragileCrops = this.config.getStringList(
                "fragileCrops", CATEGORY_GENERAL,
                // Default crops that should die in the winter
                defaultCrops.toTypedArray(),
                "A list of crops that should die out in the winter"
        )

        partialCrops = this.config.getStringList(
                "partialCrops", CATEGORY_GENERAL,
                // Default crops that be reset in the winter
                arrayOf(
                        "rustic:chili_crop",
                        "rustic:wildberry_bush"
                ),
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
