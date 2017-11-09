import net.minecraft.block.Block
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.event.world.BlockEvent
import net.minecraftforge.fml.common.eventhandler.Event
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import toughasnails.api.TANBlocks
import toughasnails.api.config.SyncedConfig
import toughasnails.api.season.Season
import toughasnails.api.season.SeasonHelper
import toughasnails.api.config.SeasonsOption
import java.util.Random


class CropFreezeHandler {
    // Random chance gen
    private val random = Random()
    private val config = ColdSnap.config

    fun killCrop(event: BlockEvent.CropGrowEvent.Pre) {
        val block = event.state.block
        val cropName = "" + block.registryName!!
        event.deny()

        if (cropName in config.fragileCrops) {
            // Cancel growth
            event.deny()
            // Change crop to dead crop
            event.world.setBlockState(event.pos, TANBlocks.dead_crops.defaultState)
        } else if (cropName in config.partialCrops) {
            // Cancel growth
            event.deny()
            event.world.setBlockState(event.pos, block.defaultState, 3)
        }

    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    fun onCropGrowPre(event: BlockEvent.CropGrowEvent.Pre) {
        val subseason = SeasonHelper.getSeasonData(event.world).subSeason
        val season = subseason.season

        // Only grow if the growth check succeeds
        if (random.nextDouble() <= config.baseGrowthChance) {
            if (season == Season.WINTER && SyncedConfig.getBooleanValue(SeasonsOption.ENABLE_SEASONS)) {
                killCrop(event)

            // Slow crop growth in late autumn
            } else if (subseason == Season.SubSeason.LATE_AUTUMN) {
                if (random.nextDouble() > config.fallFrostChance) {
                    killCrop(event)
                }
            }
        }
    }


}