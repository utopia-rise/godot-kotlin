package org.godotengine.kotlin.gradleplugin.subplugin

import com.google.auto.service.AutoService
import org.godotengine.kotlin.gradleplugin.KotlinGodotPlugin
import org.godotengine.kotlin.gradleplugin.KotlinGodotPluginExtension
import org.gradle.api.Project
import org.gradle.api.tasks.compile.AbstractCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinGradleSubplugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

@AutoService(KotlinGradleSubplugin::class)
class KotlinGodotSubplugin : KotlinGradleSubplugin<AbstractCompile> {
    override fun apply(project: Project, kotlinCompile: AbstractCompile, javaCompile: AbstractCompile?, variantData: Any?, androidProjectHandler: Any?, kotlinCompilation: KotlinCompilation<KotlinCommonOptions>?): List<SubpluginOption> {
        val extension = project.extensions.getByType(KotlinGodotPluginExtension::class.java)
        return listOf(
                SubpluginOption("library-path", extension.libraryPath),
                SubpluginOption("godot-project-path", extension.godotProjectPath)
        )
    }

    override fun isApplicable(project: Project, task: AbstractCompile): Boolean =
            project.plugins.hasPlugin(KotlinGodotPlugin::class.java)

    override fun getCompilerPluginId(): String = "GodotCompilerPlugin" //has to be the same as the plugin id in NativeTestComponentCommandLineProcessor and CommonComponentRegistrar

    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
            groupId = "org.godotengine.kotlin",
            artifactId = "kotlin-compiler-plugin",
            version = "0.1.1-3.2" // remember to bump this version in conjunction with the version in Dependencies from buildSrc
    )

    override fun getNativeCompilerPluginArtifact() = SubpluginArtifact(
            groupId = "org.godotengine.kotlin",
            artifactId = "kotlin-compiler-native-plugin",
            version = "0.1.1-3.2" // remember to bump this version in conjunction with the version in Dependencies from buildSrc
    )
}