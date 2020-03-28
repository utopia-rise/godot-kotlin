package org.godotengine.kotlin.entrygenerator

import com.squareup.kotlinpoet.FileSpec
import de.jensklingenberg.mpapt.model.Element
import org.godotengine.kotlin.entrygenerator.generator.*
import org.jetbrains.kotlin.name.Name
import java.io.File


class EntryGenerator {
    fun generateEntry(
            kaptGeneratedDirectory: String,
            libraryPath: String,
            godotProjectPath: String,
            classes: Set<Element.ClassElement>,
            properties: Set<Element.PropertyElement>,
            functions: Set<Element.FunctionElement>,
            signals: Set<Element.FunctionElement>
    ) {
        val entryFileSpec: FileSpec.Builder = FileSpec
                .builder("org.godotengine.kotlin", "Entry")
                .addImport("kotlinx.cinterop", "get") //needed for getting pointers like this: args[<number>]
                .addImport("kotlinx.cinterop", "ptr") //needed for getting pointers to `this`: options.ptr

        val gdNativeFunctionBindingGenerator = GDNativeFunctionBindingGenerator()

        classes.forEachIndexed { index, classElement ->
            val generatedConstructorBindings = classElement.generateConstructorBindings(entryFileSpec, index)
            gdNativeFunctionBindingGenerator.registerElement(classElement, bridgeFunctions = *generatedConstructorBindings)

            val generatedInternalFunctionBindings = classElement.generateInternalFunctionBindings(entryFileSpec, index)
            generatedInternalFunctionBindings.forEach { pairOfNameAndFunSpec ->
                gdNativeFunctionBindingGenerator.registerInternalFunction(classElement, pairOfNameAndFunSpec)
            }
        }

        functions.forEachIndexed { index, functionElement ->
            val generatedFunctionBridge = functionElement.func.generateFunctionBinding(entryFileSpec, index)
            gdNativeFunctionBindingGenerator.registerElement(functionElement, bridgeFunctions = *arrayOf(generatedFunctionBridge))
        }

        properties.forEachIndexed { index, propertyElement ->
            val visibleInEditor: Boolean = propertyElement
                    .annotation!! //we already know here that the annotation is present. Otherwise this property would not have been included in this set
                    .allValueArguments
                    .getValue(Name.identifier("visibleInEditor"))
                    .value as Boolean

            val generatedPropertyBindings = propertyElement.generatePropertyBinding(entryFileSpec, index)
            gdNativeFunctionBindingGenerator.registerElement(propertyElement, visibleInEditor, *generatedPropertyBindings)
        }

        signals.forEach {
            gdNativeFunctionBindingGenerator.registerElement(it)
        }

        gdNativeFunctionBindingGenerator.generateGDNativeBindingFunctions(entryFileSpec)

        GdnsFileGenerator().generateGdnsFiles(godotProjectPath, libraryPath, classes)

        entryFileSpec
                .build()
                .writeTo(File(kaptGeneratedDirectory))
    }
}