package godot.core

import godot.Object
import godot.gdnative.godot_dictionary
import godot.internal.type.CoreType
import godot.internal.type.NativeCoreType
import godot.internal.type.callNative
import kotlinx.cinterop.*


@ExperimentalUnsignedTypes
class Dictionary : NativeCoreType<godot_dictionary>, Iterable<Entry<Variant, Variant>> {
    //PROPERTIES
    val size: Int
        get() = this.size()

    val keys: VariantArray
        get() = this.keys()

    val values: VariantArray
        get() = this.values()

    //CONSTRUCTOR
    constructor() {
        callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_new)(it)
        }
    }

    constructor(other: Dictionary) {
        callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_new_copy)(it, other._handle.ptr)
        }
    }

    internal constructor(native: CValue<godot_dictionary>) {
        memScoped {
            this@Dictionary.setRawMemory(native.ptr)
        }
    }

    internal constructor(mem: COpaquePointer) {
        this.setRawMemory(mem)
    }

    //INTEROP
    override fun getRawMemory(memScope: MemScope): COpaquePointer {
        return _handle.getPointer(memScope)
    }

    override fun setRawMemory(mem: COpaquePointer) {
        _handle = mem.reinterpret<godot_dictionary>().pointed.readValue()
    }


    //API
    /**
     * Clear the dictionary, removing all key/value pairs.
     */
    fun clear() {
        callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_clear)(it)
        }
    }

    /**
     * Creates a copy of the dictionary, and returns it.
     * The deep parameter causes inner dictionaries and arrays to be copied recursively, but does not apply to objects.
     */
    fun duplicate(deep: Boolean = false) {
        callNative {
            checkNotNull(Godot.gdnative12.godot_dictionary_duplicate)(it, deep)
        }
    }

    /**
     * Returns true if the dictionary is empty.
     */
    fun empty(): Boolean {
        return callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_empty)(it)
        }
    }

    /**
     * Erase a dictionary key/value pair by key. Doesn't return a Boolean like the GDScript version because the GDNative function doesn't return anything
     */
    fun erase(key: Variant) {
        callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_erase)(it, key._handle.ptr)
        }
    }

    /**
     * Returns the current value for the specified key in the Dictionary.
     * If the key does not exist, the method returns the value of the optional default argument, or null if it is omitted.
     */
    fun get(key: Variant, default: Variant = Variant()): Variant {
        return Variant(
            callNative {
                checkNotNull(Godot.gdnative11.godot_dictionary_get_with_default)(
                    it,
                    key._handle.ptr,
                    default._handle.ptr
                )
            }
        )
    }

    fun get(key: Int, default: Variant = Variant()) = get(Variant(key), default)
    fun get(key: Int, default: Int) = get(Variant(key), Variant(default))
    fun get(key: Int, default: Float) = get(Variant(key), Variant(default))
    fun get(key: Int, default: String) = get(Variant(key), Variant(default))
    fun get(key: Int, default: Boolean) = get(Variant(key), Variant(default))
    fun get(key: Int, default: Object) = get(Variant(key), default.toVariant())
    fun get(key: Int, default: CoreType) = get(Variant(key), default.toVariant())

    fun get(key: Float, default: Variant = Variant()) = get(Variant(key), default)
    fun get(key: Float, default: Int) = get(Variant(key), Variant(default))
    fun get(key: Float, default: Float) = get(Variant(key), Variant(default))
    fun get(key: Float, default: String) = get(Variant(key), Variant(default))
    fun get(key: Float, default: Boolean) = get(Variant(key), Variant(default))
    fun get(key: Float, default: Object) = get(Variant(key), default.toVariant())
    fun get(key: Float, default: CoreType) = get(Variant(key), default.toVariant())

    fun get(key: String, default: Variant = Variant()) = get(Variant(key), default)
    fun get(key: String, default: Int) = get(Variant(key), Variant(default))
    fun get(key: String, default: Float) = get(Variant(key), Variant(default))
    fun get(key: String, default: String) = get(Variant(key), Variant(default))
    fun get(key: String, default: Boolean) = get(Variant(key), Variant(default))
    fun get(key: String, default: Object) = get(Variant(key), default.toVariant())
    fun get(key: String, default: CoreType) = get(Variant(key), default.toVariant())

    fun get(key: Boolean, default: Variant = Variant()) = get(Variant(key), default)
    fun get(key: Boolean, default: Int) = get(Variant(key), Variant(default))
    fun get(key: Boolean, default: Float) = get(Variant(key), Variant(default))
    fun get(key: Boolean, default: String) = get(Variant(key), Variant(default))
    fun get(key: Boolean, default: Boolean) = get(Variant(key), Variant(default))
    fun get(key: Boolean, default: Object) = get(Variant(key), default.toVariant())
    fun get(key: Boolean, default: CoreType) = get(Variant(key), default.toVariant())

    fun get(key: Object, default: Variant = Variant()) = get(key.toVariant(), default)
    fun get(key: Object, default: Int) = get(key.toVariant(), Variant(default))
    fun get(key: Object, default: Float) = get(key.toVariant(), Variant(default))
    fun get(key: Object, default: String) = get(key.toVariant(), Variant(default))
    fun get(key: Object, default: Boolean) = get(key.toVariant(), Variant(default))
    fun get(key: Object, default: Object) = get(key.toVariant(), default.toVariant())
    fun get(key: Object, default: CoreType) = get(key.toVariant(), default.toVariant())

    fun get(key: CoreType, default: Variant = Variant()) = get(key.toVariant(), default)
    fun get(key: CoreType, default: Int) = get(key.toVariant(), Variant(default))
    fun get(key: CoreType, default: Float) = get(key.toVariant(), Variant(default))
    fun get(key: CoreType, default: String) = get(key.toVariant(), Variant(default))
    fun get(key: CoreType, default: Boolean) = get(key.toVariant(), Variant(default))
    fun get(key: CoreType, default: Object) = get(key.toVariant(), default.toVariant())
    fun get(key: CoreType, default: CoreType) = get(key.toVariant(), default.toVariant())

    /**
     * Returns true if the dictionary has a given key.
     * Note: This is equivalent to using the in operator as follows:
     */
    fun has(key: Variant): Boolean {
        return callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_has)(it, key._handle.ptr)
        }
    }

    fun has(key: Int) = has(Variant(key))
    fun has(key: Float) = has(Variant(key))
    fun has(key: String) = has(Variant(key))
    fun has(key: Boolean) = has(Variant(key))
    fun has(key: Object) = has(key.toVariant())
    fun has(key: CoreType) = has(key.toVariant())

    /**
     * Returns true if the dictionary has all of the keys in the given array.
     */
    fun hasAll(keys: VariantArray): Boolean {
        return callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_has_all)(it, keys._handle.ptr)
        }
    }

    /**
     * Returns a hashed integer value representing the dictionary contents. This can be used to compare dictionaries by value
     */
    fun hash(): Int {
        return callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_hash)(it)
        }
    }

    /**
     * Returns the list of keys in the Dictionary.
     */
    fun keys(): VariantArray {
        return VariantArray(
            callNative {
                checkNotNull(Godot.gdnative.godot_dictionary_keys)(it)
            }
        )
    }

    /**
     * Returns the size of the dictionary (in pairs).
     */
    fun size(): Int {
        return callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_size)(it)
        }
    }

    /**
     * Returns the list of values in the Dictionary.
     */
    fun values(): VariantArray {
        return VariantArray(
            callNative {
                checkNotNull(Godot.gdnative.godot_dictionary_values)(it)
            }
        )
    }


    //UTILITIES
    operator fun get(key: Variant): Variant {
        return Variant(
            callNative {
                checkNotNull(Godot.gdnative.godot_dictionary_get)(it, key._handle.ptr)
            }
        )
    }

    operator fun get(key: Int) = get(Variant(key))
    operator fun get(key: Float) = get(Variant(key))
    operator fun get(key: String) = get(Variant(key))
    operator fun get(key: Boolean) = get(Variant(key))
    operator fun get(key: Object) = get(key.toVariant())
    operator fun get(key: CoreType) = get(key.toVariant())

    operator fun set(key: Variant, value: Variant) {
        callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_set)(it, key._handle.ptr, value._handle.ptr)
        }
    }

    operator fun set(key: Int, value: Variant) = set(Variant(key), value)
    operator fun set(key: Int, value: Int) = set(Variant(key), Variant(value))
    operator fun set(key: Int, value: Float) = set(Variant(key), Variant(value))
    operator fun set(key: Int, value: String) = set(Variant(key), Variant(value))
    operator fun set(key: Int, value: Boolean) = set(Variant(key), Variant(value))
    operator fun set(key: Int, value: Object) = set(Variant(key), value.toVariant())
    operator fun set(key: Int, value: CoreType) = set(Variant(key), value.toVariant())

    operator fun set(key: Float, value: Variant) = set(Variant(key), value)
    operator fun set(key: Float, value: Int) = set(Variant(key), Variant(value))
    operator fun set(key: Float, value: Float) = set(Variant(key), Variant(value))
    operator fun set(key: Float, value: String) = set(Variant(key), Variant(value))
    operator fun set(key: Float, value: Boolean) = set(Variant(key), Variant(value))
    operator fun set(key: Float, value: Object) = set(Variant(key), value.toVariant())
    operator fun set(key: Float, value: CoreType) = set(Variant(key), value.toVariant())

    operator fun set(key: String, value: Variant) = set(Variant(key), value)
    operator fun set(key: String, value: Int) = set(Variant(key), Variant(value))
    operator fun set(key: String, value: Float) = set(Variant(key), Variant(value))
    operator fun set(key: String, value: String) = set(Variant(key), Variant(value))
    operator fun set(key: String, value: Boolean) = set(Variant(key), Variant(value))
    operator fun set(key: String, value: Object) = set(Variant(key), value.toVariant())
    operator fun set(key: String, value: CoreType) = set(Variant(key), value.toVariant())

    operator fun set(key: Boolean, value: Variant) = set(Variant(key), value)
    operator fun set(key: Boolean, value: Int) = set(Variant(key), Variant(value))
    operator fun set(key: Boolean, value: Float) = set(Variant(key), Variant(value))
    operator fun set(key: Boolean, value: String) = set(Variant(key), Variant(value))
    operator fun set(key: Boolean, value: Boolean) = set(Variant(key), Variant(value))
    operator fun set(key: Boolean, value: Object) = set(Variant(key), value.toVariant())
    operator fun set(key: Boolean, value: CoreType) = set(Variant(key), value.toVariant())

    operator fun set(key: Object, value: Variant) = set(key.toVariant(), value)
    operator fun set(key: Object, value: Int) = set(Variant(key), Variant(value))
    operator fun set(key: Object, value: Float) = set(Variant(key), Variant(value))
    operator fun set(key: Object, value: String) = set(Variant(key), Variant(value))
    operator fun set(key: Object, value: Boolean) = set(Variant(key), Variant(value))
    operator fun set(key: Object, value: Object) = set(Variant(key), value.toVariant())
    operator fun set(key: Object, value: CoreType) = set(Variant(key), value.toVariant())

    operator fun set(key: CoreType, value: Variant) = set(key.toVariant(), value)
    operator fun set(key: CoreType, value: Int) = set(key.toVariant(), Variant(value))
    operator fun set(key: CoreType, value: Float) = set(key.toVariant(), Variant(value))
    operator fun set(key: CoreType, value: String) = set(key.toVariant(), Variant(value))
    operator fun set(key: CoreType, value: Boolean) = set(key.toVariant(), Variant(value))
    operator fun set(key: CoreType, value: Object) = set(key.toVariant(), value.toVariant())
    operator fun set(key: CoreType, value: CoreType) = set(key.toVariant(), value.toVariant())

    operator fun contains(key: Variant): Boolean = has(Variant(key))
    operator fun contains(key: Int): Boolean = has(Variant(key))
    operator fun contains(key: Float): Boolean = has(Variant(key))
    operator fun contains(key: String): Boolean = has(Variant(key))
    operator fun contains(key: Boolean): Boolean = has(Variant(key))
    operator fun contains(key: Object): Boolean = has(key.toVariant())
    operator fun contains(key: CoreType): Boolean = has(key.toVariant())

    override fun toVariant(): Variant = Variant(this)

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Dictionary) {
            return false
        }
        return callNative {
            checkNotNull(Godot.gdnative.godot_dictionary_operator_equal)(_handle.ptr, other._handle.ptr)
        }
    }

    override fun hashCode(): Int {
        return hash()
    }

    override fun toString(): String {
        return "Dictionary($size)"
    }

    internal inline fun <T> callNative(block: MemScope.(CPointer<godot_dictionary>) -> T): T {
        return callNative(this, block)
    }

    override fun iterator(): Iterator<Entry<Variant, Variant>> {
        return MapIterator(keys().iterator(), this::get)
    }
}
