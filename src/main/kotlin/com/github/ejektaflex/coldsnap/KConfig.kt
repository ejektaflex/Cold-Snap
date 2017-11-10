package com.github.ejektaflex.coldsnap

import net.minecraftforge.common.config.Configuration
import java.io.File

open class KConfig(folder: String, fileName: String) {
    internal var config: Configuration = Configuration(File(folder, fileName))
    fun load() = config.load()
    fun save() = config.save()
}