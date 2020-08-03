package godot.core

import godot.Object
import godot.gdnative.godot_array
import godot.internal.type.*
import kotlinx.cinterop.*

@ExperimentalUnsignedTypes
@PublishedApi
internal abstract class CoreArray<T : CoreType> : GodotArray<T> {

    //CONSTRUCTOR
    constructor() {
        callNative {
            nullSafe(Godot.gdnative.godot_array_new)(it)
        }
    }

    constructor(other: CoreArray<T>) {
        callNative {
            nullSafe(Godot.gdnative.godot_array_new_copy)(it, other._handle.ptr)
        }
    }

    internal constructor(native: CValue<godot_array>) {
        memScoped {
            this@CoreArray.setRawMemory(native.ptr)
        }
    }

    internal constructor(mem: COpaquePointer) {
        this.setRawMemory(mem)
    }

    //API

    override fun append(value: T) {
        callNative {
            nullSafe(Godot.gdnative.godot_array_append)(it, value.toVariant()._handle.ptr)
        }
    }

    override fun bsearch(value: T, before: Boolean): Int {
        return callNative {
            nullSafe(Godot.gdnative.godot_array_bsearch)(it, value.toVariant()._handle.ptr, before)
        }
    }

    override fun bsearchCustom(value: T, obj: Object, func: String, before: Boolean): Int {
        return callNative {
            nullSafe(Godot.gdnative.godot_array_bsearch_custom)(
                it,
                value.toVariant()._handle.ptr,
                obj.ptr,
                func.toGDString().value.ptr,
                before
            )
        }
    }

    override fun count(value: T): Int {
        return callNative {
            nullSafe(Godot.gdnative.godot_array_count)(it, value.toVariant()._handle.ptr)
        }
    }

    override fun duplicate(deep: Boolean): CoreArray<T> {
        return getCoreArray(
            callNative {
                nullSafe(Godot.gdnative11.godot_array_duplicate)(it, deep)
            }
        )
    }

    override fun erase(value: T) {
        callNative {
            nullSafe(Godot.gdnative.godot_array_erase)(it, value.toVariant()._handle.ptr)
        }
    }

    override fun find(what: T, from: Int): Int {
        return callNative {
            nullSafe(Godot.gdnative.godot_array_find)(it, what.toVariant()._handle.ptr, from)
        }
    }

    override fun findLast(value: T): Int {
        return callNative {
            nullSafe(Godot.gdnative.godot_array_find_last)(it, value.toVariant()._handle.ptr)
        }
    }

    override fun front(): T {
        return getCore(
            Variant(
                callNative {
                    nullSafe(Godot.gdnative.godot_array_front)(it)
                }
            )
        )
    }

    override fun has(value: T): Boolean {
        return callNative {
            nullSafe(Godot.gdnative.godot_array_has)(it, value.toVariant()._handle.ptr)
        }
    }

    override fun insert(position: Int, value: T) {
        return callNative {
            nullSafe(Godot.gdnative.godot_array_insert)(it, position, value.toVariant()._handle.ptr)
        }
    }

    override fun max(): T {
        return getCore(
            Variant(
                callNative {
                    nullSafe(Godot.gdnative11.godot_array_max)(it)
                }
            )
        )
    }

    override fun min(): T {
        return getCore(
            Variant(
                callNative {
                    nullSafe(Godot.gdnative11.godot_array_min)(it)
                }
            )
        )
    }

    override fun popBack(): T {
        return getCore(
            Variant(
                callNative {
                    nullSafe(Godot.gdnative.godot_array_pop_back)(it)
                }
            )
        )
    }


    override fun popFront(): T {
        return getCore(
            Variant(
                callNative {
                    nullSafe(Godot.gdnative.godot_array_pop_front)(it)
                }
            )
        )
    }

    override fun pushBack(value: T) {
        return callNative {
            nullSafe(Godot.gdnative.godot_array_push_back)(it, value.toVariant()._handle.ptr)
        }
    }

    override fun pushFront(value: T) {
        return callNative {
            nullSafe(Godot.gdnative.godot_array_push_front)(it, value.toVariant()._handle.ptr)
        }
    }

    override fun rfind(what: T, from: Int): Int {
        return callNative {
            nullSafe(Godot.gdnative.godot_array_rfind)(it, what.toVariant()._handle.ptr, from)
        }
    }

    override fun slice(begin: Int, end: Int, step: Int, deep: Boolean): CoreArray<T> {
        return getCoreArray(
            callNative {
                nullSafe(Godot.gdnative12.godot_array_slice)(it, begin, end, step, deep)
            }
        )
    }

    //UTILITIES
    internal abstract fun getCore(value: Variant): T
    internal abstract fun getCoreArray(value: CValue<godot_array>): CoreArray<T>

    override operator fun set(idx: Int, data: T) {
        callNative {
            nullSafe(Godot.gdnative.godot_array_set)(it, idx, data.toVariant()._handle.ptr)
        }
    }

    override operator fun get(idx: Int): T {
        return getCore(
            Variant(
                callNative {
                    nullSafe(Godot.gdnative.godot_array_get)(it, idx)
                }
            )
        )
    }
}