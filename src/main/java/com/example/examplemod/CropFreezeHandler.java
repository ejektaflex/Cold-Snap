package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import toughasnails.api.TANBlocks;
import toughasnails.api.config.SyncedConfig;
import toughasnails.api.season.Season;
import toughasnails.api.season.SeasonHelper;
import toughasnails.api.config.SeasonsOption;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.examplemod.ExampleMod.config;

public class CropFreezeHandler
{

    // The array of crops that should die in the winter
    private final ArrayList<String> fragileCrops = new ArrayList<>(Arrays.asList(config.fragileCrops));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onCropGrowPre(BlockEvent.CropGrowEvent.Pre event)
    {
        Block block = event.getState().getBlock();
        World world = event.getWorld();
        String cropName = "" + block.getRegistryName();
        System.out.println("Block registry name info: " + block.getRegistryName());
        Season season = SeasonHelper.getSeasonData(world).getSubSeason().getSeason();
        if (
                season == Season.WINTER &&
                fragileCrops.contains(cropName) &&
                SyncedConfig.getBooleanValue(SeasonsOption.ENABLE_SEASONS)
                ) {
            // Cancel growth
            event.setResult(Event.Result.DENY);
            // Change crop to dead crop
            event.getWorld().setBlockState(event.getPos(), TANBlocks.dead_crops.getDefaultState());
        }
    }
}