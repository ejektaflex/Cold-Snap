package com.github.ejektaflex.coldsnap

import net.minecraftforge.common.config.Configuration
import java.io.File

open class KConfig(val fileName: String) {
    lateinit internal var config: Configuration

    fun atFolder(dest: String): KConfig {
        config = Configuration(File(dest, fileName))
        return this
    }

    fun load() {
        config.load()
    }

    fun save() {
        config.save()
    }


}