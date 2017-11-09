package com.github.ejektaflex.coldsnap.Helpers

import net.minecraftforge.common.config.Configuration
import java.io.File


open class KConfig(private var configName: String) {

    lateinit var config: Configuration

    fun inPath(configDir: String): KConfig {
        config = Configuration(File(configDir, configName))
        return this
    }

    fun load() {
        this.config.load()
    }

    fun save() {
        this.config.save()
    }

}