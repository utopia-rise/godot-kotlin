package godot

import godot.core.Godot.shouldInitPtr
import godot.core.Transform
import godot.icalls._icall_Transform
import godot.icalls._icall_Unit_Transform
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any
import kotlin.Unit

open class VisualShaderNodeTransformConstant internal constructor(
  _ignore: Any?
) : VisualShaderNode(_ignore) {
  open var constant: Transform
    get() {
      val mb = getMethodBind("VisualShaderNodeTransformConstant","get_constant")
      return _icall_Transform(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("VisualShaderNodeTransformConstant","set_constant")
      _icall_Unit_Transform(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("VisualShaderNodeTransformConstant",
            "VisualShaderNodeTransformConstant")
        }

  }

  open fun constant(schedule: Transform.() -> Unit): Transform = constant.apply{
      schedule(this)
      constant = this
  }


  open fun getConstant(): Transform {
    val mb = getMethodBind("VisualShaderNodeTransformConstant","get_constant")
    return _icall_Transform( mb, this.ptr)
  }

  open fun setConstant(value: Transform) {
    val mb = getMethodBind("VisualShaderNodeTransformConstant","set_constant")
    _icall_Unit_Transform( mb, this.ptr, value)
  }

  companion object
}
