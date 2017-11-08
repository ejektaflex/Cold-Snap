package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
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
import java.util.Random;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.examplemod.ExampleMod.config;

public class CropFreezeHandler
{
    // Random chance gen
    private Random random = new Random();
    // The array of crops that should die in the winter
    private final ArrayList<String> fragileCrops = new ArrayList<>(Arrays.asList(config.fragileCrops));
    // The array of crops that should be reset in the winter
    private final ArrayList<String> partialCrops = new ArrayList<>(Arrays.asList(config.partialCrops));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onCropGrowPre(BlockEvent.CropGrowEvent.Pre event)
    {
        Block block = event.getState().getBlock();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        String cropName = "" + block.getRegistryName();
        //System.out.println("Block registry name info: " + block.getRegistryName());
        Season.SubSeason subseason = SeasonHelper.getSeasonData(world).getSubSeason();
        Season season = subseason.getSeason();

        // Only grow if the growth check succeeds
        if (random.nextDouble() <= config.baseGrowthChance) {
            if (season == Season.WINTER && SyncedConfig.getBooleanValue(SeasonsOption.ENABLE_SEASONS)) {
                if (fragileCrops.contains(cropName)) {
                    // Cancel growth
                    event.setResult(Event.Result.DENY);
                    // Change crop to dead crop
                    world.setBlockState(pos, TANBlocks.dead_crops.getDefaultState());
                } else if (partialCrops.contains(cropName)) {
                    // Cancel growth
                    event.setResult(Event.Result.DENY);
                    world.setBlockState(pos, block.getDefaultState(), 3);
                }
            // Slow crop growth in late autumn
            } else if (subseason == Season.SubSeason.LATE_AUTUMN) {
                if (random.nextDouble() > config.fallFrostChance) {
                    event.setResult(Event.Result.DENY);
                }
            }
        }

    }
}