package godot.utils

import java.util.*

object GodotBuildProperties {
    private val buildProperties by lazy {
        val props = Properties()
        props.load(GodotBuildProperties::class.java.classLoader.getResourceAsStream("build.properties"))
        props
    }

    val godotKotlinVersion by lazy {
        buildProperties["godot.kotlin.version"] as String
    }
}