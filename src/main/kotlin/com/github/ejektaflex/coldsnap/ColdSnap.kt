import com.github.ejektaflex.coldsnap.ModInfo
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent


@Mod(modid = ModInfo.ID, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDS, modLanguageAdapter = ModInfo.ADAPTER)
object ColdSnap {

    // Config instance
    lateinit var config: ConfigHandler

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        val directory = event.modConfigurationDirectory
        config = ConfigHandler(directory.path)

        MinecraftForge.EVENT_BUS.register(CropFreezeHandler)
    }

    @EventHandler
    fun init(event: FMLInitializationEvent) {
        config.load()
    }

    @EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        config.save()
    }


}
