import com.github.ejektaflex.coldsnap.ModInfo
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(
        modid = ModInfo.MODID,
        version = ModInfo.VERSION,
        dependencies = "required:toughasnails",
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
object ColdSnap {


    // GeneralConfig instance
    //lateinit var config: GeneralConfig

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        val directory = event.modConfigurationDirectory
        //config = GeneralConfig.inPath(directory.path) as GeneralConfig
        //MinecraftForge.EVENT_BUS.register(CropFreezeHandler)
    }

    @EventHandler
    fun init(event: FMLInitializationEvent) {
        //config.load()
    }

    @EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        //config.save()
    }


}
