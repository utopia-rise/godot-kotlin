package org.godotengine.kotlin.entrygenerator.generator

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import de.jensklingenberg.mpapt.common.hasAnnotation
import de.jensklingenberg.mpapt.model.Element
import de.jensklingenberg.mpapt.model.ElementUtils
import org.godotengine.kotlin.anntations.internal.RegisterInternal
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperclassesWithoutAny
import org.jetbrains.kotlin.resolve.scopes.DescriptorKindFilter

/**
 * generates all constructor and destructor bindings for a class
 * @return the function specs of those to be registered in the class registration function
 */
fun Element.ClassElement.generateConstructorBindings(entryFileSpecBuilder: FileSpec.Builder, index: Int): Array<FunSpec> {
    val returnType = ClassName(ElementUtils().getPackageOf(this), this.classDescriptor.name.asString())
    val constructorBindingFuncSpec = FunSpec
            .builder("constructorFunction${index}Constructor")
            .addModifiers(KModifier.PRIVATE)
            .returns(returnType)
            .addStatement("return·${ElementUtils().getPackageOf(this)}.${this.classDescriptor.name.asString()}()")
            .build()

    val constructorBridgeFuncSpec = FunSpec
            .builder("constructorBridge${index}")
            .addModifiers(KModifier.PRIVATE)
            .returns(getBridgeReturnType(isConstructor = true))
            .addStatement("return·%M·{·mem·->·%M(mem,·::${constructorBindingFuncSpec.name})·}", MemberName("kotlinx.cinterop", "staticCFunction"), MemberName("godot.registration", "constructFromRawMem"))
            .build()

    val destructorBridgeFuncSpec = FunSpec
            .builder("destructorBridge${index}")
            .addModifiers(KModifier.PRIVATE)
            .returns(getBridgeReturnType(isConstructor = false))
            .addStatement("return·%M·{·mem·->·%M<${returnType.canonicalName}>(mem)·}", MemberName("kotlinx.cinterop", "staticCFunction"), MemberName("godot.registration", "deconstructFromRawMem"))
            .build()

    entryFileSpecBuilder
            .addFunction(constructorBindingFuncSpec)
            .addFunction(constructorBridgeFuncSpec)
            .addFunction(destructorBridgeFuncSpec)

    return arrayOf(constructorBridgeFuncSpec, destructorBridgeFuncSpec)
}

/**
 * generated godot-library specific function bindings for internal functions that need a binding as well<br>
 * one example is the yield signal listener function which is present in each godot object, and thus in every class
 * a user can register
 */
fun Element.ClassElement.generateInternalFunctionBindings(entryFileSpecBuilder: FileSpec.Builder, index: Int): Array<Pair<Name, FunSpec>> {
    return classDescriptor
            .getAllSuperclassesWithoutAny()
            .map { it.defaultType.memberScope }
            .flatMap { it.getContributedDescriptors(DescriptorKindFilter.FUNCTIONS) }
            .filterIsInstance<CallableMemberDescriptor>() //should be callable anyway as we applied the DescriptorKindFilter.FUNCTIONS, but it's safer and like that we don't need to cast anything
            .filter { it.annotations.hasAnnotation(RegisterInternal::class.java.name) }
            .distinctBy { it.name } //to remove duplicate functions as each godot subtype inherits all functions from it's supertype
            .mapIndexed { superClassFunctionIndex, callableMemberDescriptor ->
                Pair(
                        callableMemberDescriptor.name,
                        callableMemberDescriptor.generateFunctionBinding(entryFileSpecBuilder, superClassFunctionIndex, "internalFunctionOf${index}Registration", this.classDescriptor.fqNameSafe.asString())
                )
            }
            .toTypedArray()
}

/**
 * helper function that assembles the return type for a bidge function:<br>
 * <code> CPointer<CFunction<(COpaquePointer?) -> COpaquePointer?>> </code>
 */
private fun getBridgeReturnType(isConstructor: Boolean): ParameterizedTypeName {
    return ClassName("kotlinx.cinterop", "CPointer")
            .parameterizedBy(
                    ClassName("kotlinx.cinterop", "CFunction")
                            .parameterizedBy(
                                    LambdaTypeName.get(
                                            parameters = *arrayOf(ClassName("kotlinx.cinterop", "COpaquePointer").copy(nullable = true)),
                                            returnType = if (isConstructor) ClassName("kotlinx.cinterop", "COpaquePointer").copy(nullable = true) else Unit::class.asTypeName()
                                    )
                            )
            )
}