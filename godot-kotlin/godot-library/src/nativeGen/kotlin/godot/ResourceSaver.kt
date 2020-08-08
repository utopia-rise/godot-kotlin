package godot

import godot.core.Godot
import godot.core.GodotError
import godot.core.PoolStringArray
import godot.icalls._icall_Long_String_Object_Long
import godot.icalls._icall_PoolStringArray_Object
import godot.internal.type.nullSafe
import godot.internal.utils.getMethodBind
import kotlin.Long
import kotlin.String
import kotlin.requireNotNull
import kotlinx.cinterop.cstr
import kotlinx.cinterop.invoke
import kotlinx.cinterop.memScoped

object ResourceSaver : Object() {
  init {
    memScoped {
        val ptr =
        nullSafe(Godot.gdnative.godot_global_get_singleton).invoke("ResourceSaver".cstr.ptr)
        requireNotNull(ptr) { "No instance found for singleton ResourceSaver" }
        this@ResourceSaver.ptr = ptr
    }
  }

  final const val FLAG_BUNDLE_RESOURCES: Long = 2

  final const val FLAG_CHANGE_PATH: Long = 4

  final const val FLAG_COMPRESS: Long = 32

  final const val FLAG_OMIT_EDITOR_PROPERTIES: Long = 8

  final const val FLAG_RELATIVE_PATHS: Long = 1

  final const val FLAG_REPLACE_SUBRESOURCE_PATHS: Long = 64

  final const val FLAG_SAVE_BIG_ENDIAN: Long = 16

  fun getRecognizedExtensions(type: Resource): PoolStringArray {
    val mb = getMethodBind("_ResourceSaver","get_recognized_extensions")
    return _icall_PoolStringArray_Object( mb, this.ptr, type)
  }

  fun save(
    path: String,
    resource: Resource,
    flags: Long = 0
  ): GodotError {
    val mb = getMethodBind("_ResourceSaver","save")
    return GodotError.byValue( _icall_Long_String_Object_Long( mb, this.ptr, path, resource,
        flags).toUInt())
  }

  enum class SaverFlags(
    id: Long
  ) {
    FLAG_RELATIVE_PATHS(1),

    FLAG_BUNDLE_RESOURCES(2),

    FLAG_CHANGE_PATH(4),

    FLAG_OMIT_EDITOR_PROPERTIES(8),

    FLAG_SAVE_BIG_ENDIAN(16),

    FLAG_COMPRESS(32),

    FLAG_REPLACE_SUBRESOURCE_PATHS(64);

    val id: Long
    init {
      this.id = id
    }

    companion object {
      fun from(value: Long) = values().single { it.id == value }
    }
  }
}