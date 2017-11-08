package com.example.examplemod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION, dependencies = "required:toughasnails")
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";

    // Config instance
    public static ConfigHandler config;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File directory = event.getModConfigurationDirectory();
        config = new ConfigHandler(
                new Configuration(new File(directory.getPath(), "coldsnap.cfg"))
        );

        MinecraftForge.EVENT_BUS.register(new CropFreezeHandler());
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        config.load();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        config.save();
    }

}
