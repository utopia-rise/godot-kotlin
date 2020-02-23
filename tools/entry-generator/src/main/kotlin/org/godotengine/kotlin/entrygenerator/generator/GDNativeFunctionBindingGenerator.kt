package org.godotengine.kotlin.entrygenerator.generator

import com.squareup.kotlinpoet.*
import de.jensklingenberg.mpapt.common.findAnnotation
import de.jensklingenberg.mpapt.common.hasAnnotation
import de.jensklingenberg.mpapt.model.Element
import org.godotengine.kotlin.annotation.*
import org.godotengine.kotlin.entrygenerator.mapper.RpcModeAnnotationMapper
import org.godotengine.kotlin.entrygenerator.mapper.TypeHintMapper
import org.godotengine.kotlin.entrygenerator.model.getVariantType
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.constants.KClassValue
import org.jetbrains.kotlin.resolve.constants.StringValue
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.types.asSimpleType

class GDNativeFunctionBindingGenerator {
    private val nativeScriptInitFunctionBuilder: FunSpec.Builder = createNativeScriptInitFunctionBuilder()

    fun registerElement(element: Element, visibleInEditor: Boolean = true, vararg bridgeFunctions: FunSpec) {
        when (element) {
            is Element.ClassElement -> nativeScriptInitFunctionBuilder.addStatement("%M(\"${getFullClassName(element)}\",·\"${getBaseClassOfClass(element)}\"${convertBridgeFunctionsToString(bridgeFunctions)})", MemberName("godot.registration", "registerClass"))
            is Element.PropertyElement -> nativeScriptInitFunctionBuilder.addStatement("%M(\"${getFullClassName(element)}\",·\"${element.propertyDescriptor.name}\",·$visibleInEditor${convertBridgeFunctionsToString(bridgeFunctions)}${getRpcMode(element)}${getPropertyHints(element)})", MemberName("godot.registration", "registerProperty"))
            is Element.FunctionElement -> {
                if (element.func.hasAnnotation(RegisterSignal::class.java.name)) {
                    nativeScriptInitFunctionBuilder.addStatement("%M(\"${getFullClassName(element, true)}\",·\"${element.simpleName}\"${getSignalArgumentsAsString(element)}${getSignalDefaultArgumentsAsString(element)})", MemberName("godot.registration", "registerSignal"))
                } else {
                    nativeScriptInitFunctionBuilder.addStatement("%M(\"${getFullClassName(element)}\",·\"${element.simpleName}\"${convertBridgeFunctionsToString(bridgeFunctions)}${getRpcMode(element)})", MemberName("godot.registration", "registerMethod"))
                }
            }
            else -> throw IllegalArgumentException("Element of kind ${element.elementKind} is not registrable")
        }
    }

    private fun getPropertyHints(element: Element.PropertyElement): String {
        val allValueArguments = element
                .propertyDescriptor
                .annotations
                .findAnnotation(RegisterProperty::class.java.asClassName().canonicalName)!!
                .allValueArguments

        @Suppress("UNCHECKED_CAST") //see comment at cast
        val typeHintAsString = if(allValueArguments.containsKey(Name.identifier("propertyHint"))) {
            val typeHintAsEnumPair = ((allValueArguments.getValue(Name.identifier("propertyHint"))).value as Pair<ClassId, Name>) //represents an enum. If it cannot be cast, something is wrong and it should fail hard
            "${typeHintAsEnumPair.first.asString().replace("/", ".")}.${typeHintAsEnumPair.second}"
        } else {
            null
        }
        val hintString = if(allValueArguments.containsKey(Name.identifier("hintString"))) {
            allValueArguments.getValue(Name.identifier("hintString")).value as String
        } else {
            null
        }

        return buildString {
            if (typeHintAsString != null && typeHintAsString != "godot.registration.PropertyHint.None") {
                append(",·propertyHint·=·$typeHintAsString")
            }
            if (hintString != null && hintString.isNotEmpty()) {
                append(",·hintString·=·\"$hintString\"")
            }
        }
    }

    private fun getRpcMode(element: Element.FunctionElement): String {
        val annotations = element.func.annotations.map { it.fqName!!.asString() }
        val registerAnnotation = element.func.annotations.findAnnotation(RegisterFunction::class.java.asClassName().canonicalName)
        return buildRpcModeString(element, annotations, registerAnnotation)
    }

    private fun getRpcMode(element: Element.PropertyElement): String {
        val annotations = element.propertyDescriptor.annotations.map { it.fqName!!.asString() }
        val registerAnnotation = element.propertyDescriptor.annotations.findAnnotation(RegisterProperty::class.java.asClassName().canonicalName)
        return buildRpcModeString(element, annotations, registerAnnotation)
    }

    private fun buildRpcModeString(element: Element, annotations: List<String>, registerAnnotation: AnnotationDescriptor?): String {
        var rpcAnnotation = rpcAnnotations()
                .map { it.asTypeName().canonicalName }
                .firstOrNull { annotations.contains(it) }

        if (registerAnnotation == null && rpcAnnotation != null) {
            throw IllegalArgumentException("$element is annotated with an rpc mode but is not registered with a Register annotation!\nAll elements with an rpc mode have to be registered as well")
        }

        if (rpcAnnotation == null) {
            val rpcModeAnnotation = registerAnnotation!!
                    .allValueArguments
                    .filterKeys { it.asString() == "rpcMode" }
                    .ifEmpty { null }
                    ?.getValue(Name.identifier("rpcMode"))
                    ?.value

            if (rpcModeAnnotation != null) {
                val enumMapping = (rpcModeAnnotation as Pair<ClassId, Name>) //represents an enum. If it cannot be cast, something is wrong and it should fail hard
                rpcAnnotation = "${enumMapping.first.asString().replace("/", ".")}.${enumMapping.second}"
            }
        }

        return buildString {
            if (rpcAnnotation != null) {
                append(",·rpcMode·=·${RpcModeAnnotationMapper.mapRpcModeAnnotationToClassName(rpcAnnotation)}")
            }
        }
    }

    fun registerInternalFunction(classElement: Element.ClassElement, pairOfNameAndFunSpec: Pair<Name, FunSpec>) {
        nativeScriptInitFunctionBuilder.addStatement("%M(\"${getFullClassName(classElement)}\",·\"${pairOfNameAndFunSpec.first.asString()}\",·${pairOfNameAndFunSpec.second.name}())", MemberName("godot.registration", "registerMethod"))
    }

    fun generateGDNativeBindingFunctions(entryFileSpecBuilder: FileSpec.Builder) {
        entryFileSpecBuilder
                .addFunction(generateGDNativeInitFunction())
                .addFunction(generateGDNativeTerminateFunction())
                .addFunction(nativeScriptInitFunctionBuilder.build())
    }


    private fun getSignalArgumentsAsString(element: Element.FunctionElement): String {
        val arguments = element
                .func
                .valueParameters
                .map {
                    val name = it.name.asString()
                    val type = it.type.toString().replace("?", "").getVariantType()
                    Pair(name, type)
                }

        val signalArgumentsArrayString = buildString {
            append(",·arrayOf(")
            arguments.forEachIndexed { index, pair ->
                append("${pair.first}·to·${pair.second}")
                if (index != arguments.size - 1) {
                    append(",·")
                }
            }
            append(")")
        }

        return if (arguments.isNotEmpty()) {
            signalArgumentsArrayString
        } else {
            ""
        }
    }

    private fun getSignalDefaultArgumentsAsString(element: Element.FunctionElement): String {
        val defaultArguments = element
                .func
                .findAnnotation(RegisterSignal::class.java.name)
                ?.allValueArguments
                ?.values
                ?.flatMap { it.value as ArrayList<*> }
                ?.map { (it as StringValue).value }


        if (defaultArguments?.size != element.func.valueParameters.size) {
            throw IllegalArgumentException("Amount of default arguments provided does not match the amount of arguments of the signal")
        }

        val signalArgumentsArrayString = buildString {
            append(",·arrayOf(")
            defaultArguments.forEachIndexed { index, defaultArgument ->
                append("Variant($defaultArgument)")
                if (index != defaultArguments.size - 1) {
                    append(",·")
                }
            }
            append(")")
        }

        return if (defaultArguments.isNotEmpty()) {
            signalArgumentsArrayString
        } else {
            ""
        }
    }

    private fun convertBridgeFunctionsToString(bridgeFunctions: Array<out FunSpec>): String {
        return buildString {
            if (bridgeFunctions.isNotEmpty()) {
                append(",·")
            }
            bridgeFunctions.forEachIndexed { index, funSpec ->
                append("${funSpec.name}()")
                if (index != bridgeFunctions.size - 1) {
                    append(",·")
                }
            }
        }
    }

    private fun getBaseClassOfClass(element: Element.ClassElement): String {
        return element.classDescriptor.typeConstructor.supertypes.first().asSimpleType().toString()
    }

    private fun getFullClassName(element: Element.ClassElement): String {
        return element.classDescriptor.fqNameSafe.asString()
    }

    private fun getFullClassName(element: Element.FunctionElement, isSignal: Boolean = false): String {
        return if (isSignal) {
            element.func.containingDeclaration.containingDeclaration!!.fqNameSafe.asString()
        } else {
            element.func.containingDeclaration.fqNameSafe.asString()
        }
    }

    private fun getFullClassName(element: Element.PropertyElement): String {
        return element.propertyDescriptor.containingDeclaration.fqNameSafe.asString()
    }

    private fun createNativeScriptInitFunctionBuilder(): FunSpec.Builder {
        return FunSpec
                .builder("NativeScriptInit")
                .addAnnotation(
                        AnnotationSpec
                                .builder(ClassName("kotlin.native", "CName"))
                                .addMember("%S", "godot_nativescript_init")
                                .build()
                )
                .addParameter("handle", ClassName("kotlinx.cinterop", "COpaquePointer"))
                .addStatement("%M(handle)", MemberName("godot.gdnative", "godot_wrapper_nativescript_init"))
    }

    private fun generateGDNativeInitFunction(): FunSpec {
        return FunSpec
                .builder("GDNativeInit")
                .addAnnotation(
                        AnnotationSpec
                                .builder(ClassName("kotlin.native", "CName"))
                                .addMember("%S", "godot_gdnative_init")
                                .build()
                )
                .addParameter("options", ClassName("godot.gdnative", "godot_gdnative_init_options"))
                .addStatement("%M(options.ptr)", MemberName("godot.gdnative", "godot_wrapper_gdnative_init"))
                .build()
    }

    private fun generateGDNativeTerminateFunction(): FunSpec {
        return FunSpec
                .builder("GDNativeTerminate")
                .addAnnotation(
                        AnnotationSpec
                                .builder(ClassName("kotlin.native", "CName"))
                                .addMember("%S", "godot_gdnative_terminate")
                                .build()
                )
                .addParameter("options", ClassName("godot.gdnative", "godot_gdnative_terminate_options"))
                .addStatement("%M(options.ptr)", MemberName("godot.gdnative", "godot_wrapper_gdnative_terminate"))
                .build()
    }
}