package com.github.ejektaflex.coldsnap.Helpers

import net.minecraftforge.common.config.Configuration

abstract class KConfigBuilder(var root: Configuration) {

}



class KConfigFile(private val c: Configuration) {
    fun category(name: String, init: KConfigCategory.() -> Unit): KConfigCategory {
        val cat = KConfigCategory(c, name)
        cat.init()
        return cat
    }
}

fun configFile(configuration: Configuration, init: KConfigFile.() -> Unit): KConfigFile {
    val file = KConfigFile(configuration)
    file.init()
    return file
}

class KConfigCategory(private val c: Configuration, private val cat_name: String) {

    operator fun set(key: String, value: Any) {
        when (value) {
            is Boolean -> c.get(cat_name, key, value).boolean
        }
    }


}

class KConfigOption(value: Any) {

}


