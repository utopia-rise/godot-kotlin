package godot

import godot.core.Godot.shouldInitPtr
import godot.icalls._icall_Double
import godot.icalls._icall_Unit_Double
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any
import kotlin.Double

open class CapsuleShape2D internal constructor(
  _ignore: Any?
) : Shape2D(_ignore) {
  open var height: Double
    get() {
      val mb = getMethodBind("CapsuleShape2D","get_height")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("CapsuleShape2D","set_height")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var radius: Double
    get() {
      val mb = getMethodBind("CapsuleShape2D","get_radius")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("CapsuleShape2D","set_radius")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("CapsuleShape2D", "CapsuleShape2D")
        }

  }

  open fun getHeight(): Double {
    val mb = getMethodBind("CapsuleShape2D","get_height")
    return _icall_Double( mb, this.ptr)
  }

  open fun getRadius(): Double {
    val mb = getMethodBind("CapsuleShape2D","get_radius")
    return _icall_Double( mb, this.ptr)
  }

  open fun setHeight(height: Double) {
    val mb = getMethodBind("CapsuleShape2D","set_height")
    _icall_Unit_Double( mb, this.ptr, height)
  }

  open fun setRadius(radius: Double) {
    val mb = getMethodBind("CapsuleShape2D","set_radius")
    _icall_Unit_Double( mb, this.ptr, radius)
  }

  companion object
}
