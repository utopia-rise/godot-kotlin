package godot.compiler.plugin

import com.intellij.mock.MockProject
import de.jensklingenberg.mpapt.common.MpAptProject
import godot.annotation.processor.GodotAnnotationProcessor
import org.jetbrains.kotlin.codegen.extensions.ClassBuilderInterceptorExtension
import org.jetbrains.kotlin.compiler.plugin.*
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor
import org.jetbrains.kotlin.js.translate.extensions.JsSyntheticTranslateExtension

class CommonComponentRegistrar : ComponentRegistrar {
    override fun registerProjectComponents(
        project: MockProject,
        configuration: CompilerConfiguration
    ) {
        val enabled = checkNotNull(configuration.get(CompilerPluginConst.CommandlineArguments.ENABLED)) {
            "enabled parameter missing"
        }
        if (enabled) {
            val processor = GodotAnnotationProcessor(
                checkNotNull(configuration.get(CompilerPluginConst.CommandlineArguments.ENTRY_DIR_PATH)) { "No path for generated entry file specified" },
                checkNotNull(configuration.get(CompilerPluginConst.CommandlineArguments.GDNS_DIR_PATH)) { "No path for generated gdns files specified" }
            )
            val mpapt = MpAptProject(processor, configuration)
            StorageComponentContainerContributor.registerExtension(project, mpapt)
            ClassBuilderInterceptorExtension.registerExtension(project, mpapt)
            JsSyntheticTranslateExtension.registerExtension(project, mpapt)
        }
    }
}

class CommonGodotKotlinCompilerPluginCommandLineProcessor : CommandLineProcessor {
    companion object {
        val GDNS_DIR_PATH_OPTION = CliOption(
            CompilerPluginConst.CommandLineOptionNames.gdnsDirPathOption,
            "Path to where the generated gdns files should be written to",
            CompilerPluginConst.CommandlineArguments.GDNS_DIR_PATH.toString(),
            required = true,
            allowMultipleOccurrences = false
        )

        val ENTRY_DIR_PATH_OPTION = CliOption(
            CompilerPluginConst.CommandLineOptionNames.entryDirPathOption,
            "Path to where the generated entry file should be written to",
            CompilerPluginConst.CommandlineArguments.ENTRY_DIR_PATH.toString(),
            required = true,
            allowMultipleOccurrences = false
        )

        val ENABLED = CliOption(
            CompilerPluginConst.CommandLineOptionNames.enabledOption,
            "Flag to enable entry generation",
            CompilerPluginConst.CommandlineArguments.ENABLED.toString(),
            required = true,
            allowMultipleOccurrences = false
        )

        const val PLUGIN_ID = CompilerPluginConst.compilerPluginId
    }

    override val pluginId = PLUGIN_ID
    override val pluginOptions = listOf(
        GDNS_DIR_PATH_OPTION,
        ENTRY_DIR_PATH_OPTION,
        ENABLED
    )

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) {
        return when (option) {
            GDNS_DIR_PATH_OPTION -> configuration.put(
                CompilerPluginConst.CommandlineArguments.GDNS_DIR_PATH, value
            )
            ENTRY_DIR_PATH_OPTION -> configuration.put(
                CompilerPluginConst.CommandlineArguments.ENTRY_DIR_PATH, value
            )
            ENABLED -> configuration.put(
                CompilerPluginConst.CommandlineArguments.ENABLED, value.toBoolean()
            )
            else -> throw CliOptionProcessingException("Unknown option: ${option.optionName}")
        }
    }
}
