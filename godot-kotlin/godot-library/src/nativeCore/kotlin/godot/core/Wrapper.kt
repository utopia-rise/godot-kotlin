package godot.core

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.asStableRef

// I have no why this exist, just copied it over from the cpp binding when implementing the cast system.

class Wrapped(val instance: COpaquePointer, val tag: COpaquePointer)

fun createWrapper(data: COpaquePointer?, tag: COpaquePointer?, instance: COpaquePointer?): COpaquePointer? {
    val wrapped = Wrapped(
        checkNotNull(instance),
        checkNotNull(tag)
    )
    return StableRef.create(wrapped).asCPointer()
}

fun destroyWrapper(data: COpaquePointer?, wrapper: COpaquePointer?) {
    wrapper?.asStableRef<Wrapped>()?.dispose()
}