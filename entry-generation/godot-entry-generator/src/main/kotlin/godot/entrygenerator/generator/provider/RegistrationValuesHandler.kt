package godot.entrygenerator.generator.provider

import com.squareup.kotlinpoet.ClassName
import godot.entrygenerator.extension.assignmentPsi
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.js.resolve.diagnostics.findPsi
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject
import org.jetbrains.kotlin.psi.psiUtil.referenceExpression
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.bindingContextUtil.getReferenceTargets
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor

abstract class RegistrationValuesHandler(
    val propertyDescriptor: PropertyDescriptor,
    val bindingContext: BindingContext
) {
    abstract fun getPropertyTypeHint(): ClassName
    abstract fun getHintString(): String

    fun getDefaultValue(): Pair<String, Array<Any>> {
        if (propertyDescriptor.isLateInit) {
            return "%L" to arrayOf("null")
        }
        val defaultValue = getDefaultValueExpression(propertyDescriptor.assignmentPsi)
        if (defaultValue == null) {
            throw IllegalStateException("") //TODO: error
        }
        val params = mutableListOf<Any>()
        params.add(ClassName("godot.core", "Variant"))
        params.addAll(defaultValue.second)
        return "%T(${defaultValue.first})" to params.toTypedArray()
    }

    private fun getDefaultValueExpression(expression: KtExpression): Pair<String, Array<Any>>? {
        if (expression is KtConstantExpression) {
            return return "%L" to arrayOf(expression.text)
        } else if (expression is KtStringTemplateExpression && !expression.hasInterpolation()) {
            return return "%S" to arrayOf(expression.text)
        } else if (expression is KtDotQualifiedExpression) {
            val receiver = expression.receiverExpression
            val receiverRef = receiver.getReferenceTargets(bindingContext).firstOrNull()

            if (receiverRef != null) {
                val psi = receiverRef.findPsi()
                // TODO: receiver ref might be a deserialized descriptor, fix this once we have core classes
                if (psi is KtClass && psi.isEnum()) {
                    val fqName = psi.fqName
                    require(fqName != null)
                    val pkg = fqName.parent().asString()
                    val className = fqName.shortName().asString()
                    return "%T.%L" to arrayOf(ClassName(pkg, className), expression.selectorExpression!!.text)
                }
            }
        } else if (expression is KtCallExpression) {
            val ref = expression.referenceExpression()?.getReferenceTargets(bindingContext)
                ?.firstOrNull()

            if (ref != null) {
                val psi = ref.findPsi()
                val transformedArgs = expression.valueArguments.mapNotNull { it.getArgumentExpression() }
                    .map { getDefaultValueExpression(it) }

                // if an arg is null, then it means that it contained a non static reference
                var hasNullArg = false
                for (arg in transformedArgs) {
                    if (arg == null) {
                        hasNullArg = true
                        break
                    }
                }

                if (psi is KtConstructor<*> && !hasNullArg ) {
                    val fqName = psi.containingClassOrObject!!.fqName
                    require(fqName != null)
                    val pkg = fqName.parent().asString()
                    val className = fqName.shortName().asString()
                    val params = mutableListOf<Any>()
                    params.add(ClassName(pkg, className))
                    transformedArgs.forEach { params.addAll(it!!.second) }
                    return "%T(${transformedArgs.joinToString { it!!.first }})" to params.toTypedArray()
                } else if (ref is DeserializedClassConstructorDescriptor && !hasNullArg) {
                    val fqName = ref.constructedClass.fqNameSafe
                    val pkg = fqName.parent().asString()
                    val className = fqName.shortName().asString()
                    val params = mutableListOf<Any>()
                    params.add(ClassName(pkg, className))
                    transformedArgs.forEach { params.addAll(it!!.second) }
                    return "%T(${transformedArgs.joinToString { it!!.first }})" to params.toTypedArray()
                }
            }
        }

        return null
    }
}
