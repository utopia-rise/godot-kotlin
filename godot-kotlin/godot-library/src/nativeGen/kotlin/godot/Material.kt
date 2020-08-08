package godot

import godot.icalls._icall_Long
import godot.icalls._icall_Material
import godot.icalls._icall_Unit_Long
import godot.icalls._icall_Unit_Object
import godot.internal.utils.getMethodBind
import kotlin.Any
import kotlin.Long

open class Material internal constructor(
  _ignore: Any?
) : Resource(_ignore) {
  open var nextPass: Material
    get() {
      val mb = getMethodBind("Material","get_next_pass")
      return _icall_Material(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("Material","set_next_pass")
      _icall_Unit_Object(mb, this.ptr, value)
    }

  open var renderPriority: Long
    get() {
      val mb = getMethodBind("Material","get_render_priority")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("Material","set_render_priority")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  internal constructor() : this(null)

  open fun getNextPass(): Material {
    val mb = getMethodBind("Material","get_next_pass")
    return _icall_Material( mb, this.ptr)
  }

  open fun getRenderPriority(): Long {
    val mb = getMethodBind("Material","get_render_priority")
    return _icall_Long( mb, this.ptr)
  }

  open fun setNextPass(nextPass: Material) {
    val mb = getMethodBind("Material","set_next_pass")
    _icall_Unit_Object( mb, this.ptr, nextPass)
  }

  open fun setRenderPriority(priority: Long) {
    val mb = getMethodBind("Material","set_render_priority")
    _icall_Unit_Long( mb, this.ptr, priority)
  }

  companion object {
    final const val RENDER_PRIORITY_MAX: Long = 127

    final const val RENDER_PRIORITY_MIN: Long = -128
  }
}