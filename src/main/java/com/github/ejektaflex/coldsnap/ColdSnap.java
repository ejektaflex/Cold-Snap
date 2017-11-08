package com.github.ejektaflex.coldsnap;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = ColdSnap.MODID, version = ColdSnap.VERSION, dependencies = "required:toughasnails")
public class ColdSnap
{
    public static final String MODID = "coldsnap";
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
        // some github code
        config.load();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        config.save();
    }

}
