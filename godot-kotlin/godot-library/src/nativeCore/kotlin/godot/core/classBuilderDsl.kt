package godot.core

import godot.registration.RPCMode
import kotlinx.cinterop.StableRef

@DslMarker
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class ClassBuilderDSL

@ClassBuilderDSL
class ClassBuilder<T: Object> internal constructor(private val classHandle: ClassHandle<T>) {
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
}