package com.github.ejektaflex.coldsnap.Helpers

import com.github.ejektaflex.coldsnap.ModInfo
import net.minecraftforge.common.config.Config
import net.minecraftforge.common.config.ConfigManager
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent




open class KConfig {

    @Mod.EventBusSubscriber(modid = ModInfo.MODID)
    private class EventHandler {
        @SubscribeEvent
        fun onConfigChanged(event: ConfigChangedEvent.OnConfigChangedEvent) {
            if (event.modID == ModInfo.MODID) {
                ConfigManager.sync(ModInfo.MODID, Config.Type.INSTANCE)
            }
        }
    }

}