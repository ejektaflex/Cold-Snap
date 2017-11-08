package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import toughasnails.api.config.SyncedConfig;
import toughasnails.api.season.Season;
import toughasnails.api.season.SeasonHelper;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.config.SeasonsOption;

public class CropFreezeHandler
{
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onCropGrowPre(BlockEvent.CropGrowEvent.Pre event)
    {
        Block block = event.getState().getBlock();
        event.getState().getBlock();
        BlockPos pos = event.getPos();
        World world = event.getWorld();
        System.out.println("Block registry name info: " + block.getRegistryName());
        Season season = SeasonHelper.getSeasonData(world).getSubSeason().getSeason();
        if (season == Season.WINTER &&
                !TemperatureHelper.isPosClimatisedForTemp(world, pos, new Temperature(1)) &&
                SyncedConfig.getBooleanValue(SeasonsOption.ENABLE_SEASONS)
                ) {
            event.setResult(Event.Result.DENY);
        }
    }
}