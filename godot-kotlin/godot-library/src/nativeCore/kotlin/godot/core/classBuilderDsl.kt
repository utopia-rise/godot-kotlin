package godot.core

import godot.Object
import godot.gdnative.godot_property_hint
import godot.internal.type.toNaturalT
import godot.registration.RPCMode
import kotlinx.cinterop.StableRef
import kotlin.reflect.KMutableProperty1

@DslMarker
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class ClassBuilderDSL

@ClassBuilderDSL
class ClassBuilder<T : Object> internal constructor(val classHandle: ClassHandle<T>) {
    fun <R> function(name: String, rpcMode: RPCMode, body: T.() -> R) {
        val function = Function0(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun <P0, R> function(name: String, rpcMode: RPCMode, body: T.(P0) -> R) {
        val function = Function1(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun <P0, P1, R> function(name: String, rpcMode: RPCMode, body: T.(P0, P1) -> R) {
        val function = Function2(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun <P0, P1, P2, R> function(name: String, rpcMode: RPCMode, body: T.(P0, P1, P2) -> R) {
        val function = Function3(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun <P0, P1, P2, P3, R> function(name: String, rpcMode: RPCMode, body: T.(P0, P1, P2, P3) -> R) {
        val function = Function4(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun <P0, P1, P2, P3, P4, R> function(
        name: String,
        rpcMode: RPCMode,
        body: T.(P0, P1, P2, P3, P4) -> R
    ) {
        val function = Function5(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun <P0, P1, P2, P3, P4, P5, R> function(
        name: String,
        rpcMode: RPCMode,
        body: T.(P0, P1, P2, P3, P4, P5) -> R
    ) {
        val function = Function6(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun <P0, P1, P2, P3, P4, P5, P6, R> function(
        name: String,
        rpcMode: RPCMode,
        body: T.(P0, P1, P2, P3, P4, P5, P6) -> R
    ) {
        val function = Function7(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun <P0, P1, P2, P3, P4, P5, P6, P7, R> function(
        name: String,
        rpcMode: RPCMode,
        body: T.(P0, P1, P2, P3, P4, P5, P6, P7) -> R
    ) {
        val function = Function8(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, R> function(
        name: String,
        rpcMode: RPCMode,
        body: T.(P0, P1, P2, P3, P4, P5, P6, P7, P8) -> R
    ) {
        val function = Function9(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, R> function(
        name: String,
        rpcMode: RPCMode,
        body: T.(P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R
    ) {
        val function = Function10(body)
        classHandle.registerFunction(name, StableRef.create(function).asCPointer(), rpcMode)
    }

    fun signal(name: String, parameters: Map<String, Variant.Type>) {
        classHandle.registerSignal(name, parameters)
    }

    fun <K : Any> property(
        name: String,
        property: KMutableProperty1<T, K>,
        type: Variant.Type,
        default: Variant? = null,
        isVisibleInEditor: Boolean = true,
        rpcMode: RPCMode,
        hintType: godot_property_hint = godot_property_hint.GODOT_PROPERTY_HINT_NONE,
        hintString: String = ""
    ) {
        val propertyHandler = MutablePropertyHandler(property)
        classHandle.registerProperty(
            name,
            StableRef.create(propertyHandler).asCPointer(),
            type,
            default,
            isVisibleInEditor,
            rpcMode,
            hintType,
            hintString
        )
    }

    inline fun <reified K : Enum<K>> enumProperty(
        name: String,
        property: KMutableProperty1<T, K>,
        default: Variant? = null,
        isVisibleInEditor: Boolean = true,
        rpcMode: RPCMode
    ) {
        val propertyHandler = MutableEnumPropertyHandler(property) { ord -> enumValues<K>()[ord] }
        classHandle.registerProperty(
            name,
            StableRef.create(propertyHandler).asCPointer(),
            Variant.Type.STRING,
            default,
            isVisibleInEditor,
            rpcMode,
            godot_property_hint.GODOT_PROPERTY_HINT_ENUM,
            enumValues<K>().joinToString { it.name }
        )
    }


    inline fun <reified K : Enum<K>> enumListProperty(
        name: String,
        property: KMutableProperty1<T, EnumArray<K>>,
        default: EnumArray<K>? = null,
        isVisibleInEditor: Boolean = true,
        rpcMode: RPCMode
    ) {
        val variantArray = IntVariantArray()
        if (default != null) {
            default.forEach {
                variantArray.append(it.ordinal.toNaturalT())
            }
        }
        val propertyHandler = MutablePropertyHandler(property)
        classHandle.registerProperty(
            name,
            StableRef.create(propertyHandler).asCPointer(),
            Variant.Type.ARRAY,
            Variant(variantArray),
            isVisibleInEditor,
            rpcMode,
            godot_property_hint.GODOT_PROPERTY_HINT_ENUM,
            "2/3:${enumValues<K>().joinToString(",") { it.name }}" //2 = Variant.Type.Int.ordinal | 3 = PropertyHint.PROPERTY_HINT_ENUM.ordinal
        )
    }

    inline fun <reified K : Enum<K>> enumFlagProperty(
        name: String,
        property: KMutableProperty1<T, Set<K>>,
        default: Variant? = null,
        isVisibleInEditor: Boolean = true,
        rpcMode: RPCMode
    ) {
        val variantArray = IntVariantArray()
        if (default != null) {
            default.asVariantArray().forEach {
                variantArray.append((it as K).ordinal.toNaturalT())
            }
        }
        val propertyHandler = MutableEnumFlagPropertyHandler(property) { ord -> enumValues<K>()[ord.toInt()] }
        classHandle.registerProperty(
            name,
            StableRef.create(propertyHandler).asCPointer(),
            Variant.Type.INT,
            Variant(variantArray),
            isVisibleInEditor,
            rpcMode,
            godot_property_hint.GODOT_PROPERTY_HINT_FLAGS,
            "int,FLAGS,${enumValues<K>().joinToString { it.name }}"
        )
    }
}
