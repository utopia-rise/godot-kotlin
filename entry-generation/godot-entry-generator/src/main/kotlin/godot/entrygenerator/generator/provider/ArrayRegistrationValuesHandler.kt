package godot.entrygenerator.generator.provider

import com.squareup.kotlinpoet.ClassName
import godot.entrygenerator.exceptions.WrongAnnotationUsageException
import godot.entrygenerator.extension.*
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.js.descriptorUtils.getJetTypeFqName
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.isEnum

class ArrayRegistrationValuesHandler(
    propertyDescriptor: PropertyDescriptor,
    bindingContext: BindingContext
) : RegistrationValuesHandler(propertyDescriptor, bindingContext) {
    override fun getPropertyTypeHint(): ClassName {
        return when (propertyHintAnnotation?.fqName?.asString()) {
            null -> ClassName("godot.gdnative.godot_property_hint", "GODOT_PROPERTY_HINT_NONE")
            else -> throw WrongAnnotationUsageException(propertyDescriptor, propertyHintAnnotation)
        }
    }

    override fun getHintString(): String {
        // at this point we know type is a VariantArray
        val type = propertyDescriptor.type

        val elementType = type.arguments.firstOrNull()?.type

        return when {
            elementType != null && KotlinBuiltIns.isAny(elementType) -> ""
            elementType != null && elementType.isEnum() -> {
                // return value is not used, hint is computed at runtime
                ""
            }
            else -> {
                var hintBuilder = "Array"
                var currentElementType: KotlinType? = elementType

                if (currentElementType == null) {
                    val compatibleListType = type.getCompatibleListType()
                    if (compatibleListType.isNotEmpty()) {
                        hintBuilder += ",${type.getCompatibleListType()}"
                    }
                }

                loop@ while (currentElementType != null) {
                    when {
                        currentElementType.isCompatibleList() -> {
                            hintBuilder += ",Array"
                            currentElementType = currentElementType.arguments.firstOrNull()?.type
                        }
                        currentElementType.getJetTypeFqName(false).isGodotPrimitive() -> {
                            hintBuilder += ",${currentElementType.getJetTypeFqName(false).getAsGodotPrimitive()}"
                            break@loop
                        }
                        currentElementType.isCoreType() -> {
                            hintBuilder += ",${currentElementType.getAsCoreType()}"
                            break@loop
                        }
                        else -> {
                            hintBuilder = ""
                            break@loop
                        }
                    }
                }
                hintBuilder
            }
        }
    }
}
