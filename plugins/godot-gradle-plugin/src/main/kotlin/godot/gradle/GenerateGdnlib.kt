package godot.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.mapProperty
import org.gradle.kotlin.dsl.property
import java.io.File

open class GenerateGdnlib : DefaultTask() {
    @Input
    val libraries = project.objects.mapProperty<Platform, File>()

    @Input
    val singleton = project.objects.property<Boolean>()

    @Input
    val loadOnce = project.objects.property<Boolean>()

    @Input
    val reloadable = project.objects.property<Boolean>()

    @OutputFile
    val output = project.objects.fileProperty()

    @TaskAction
    fun generate() {
        val file = output.get().asFile
        file.delete()
        file.bufferedWriter().use { writer ->
            writer.appendln("[entry]")
            writer.appendln()
            libraries.get().forEach { (platform, path) ->
                writer.appendln("${platformToKey(platform)}=\"res://$path\"")
            }
            writer.appendln()

            writer.appendln("[dependencies]")
            writer.appendln()
            libraries.get().forEach { (platform, _) ->
                writer.appendln("${platformToKey(platform)}=[  ]")
            }
            writer.appendln()

            writer.appendln("[general]")
            writer.appendln()
            writer.appendln("singleton=${singleton.get()}")
            writer.appendln("load_once=${loadOnce.get()}")
            writer.appendln("symbol_prefix=\"godot_\"")
            writer.appendln("reloadable=${reloadable.get()}")
        }
    }

    private fun platformToKey(platform: Platform): String {
        return when (platform) {
            Platform.WINDOWS_X64 -> "Windows.64"
            Platform.LINUX_X64 -> "X11.64"
            Platform.OSX_X64 -> "OSX.64"
            Platform.ANDROID_X64 -> "Android.x86_64"
            Platform.ANDROID_ARM64 -> "Android.arm64-v8a"
            Platform.IOS_X64 -> "iOS.64"
            Platform.IOS_ARM64 -> "iOS.arm64"
        }
    }
}
