package godot

import godot.core.Godot.shouldInitPtr
import godot.icalls._icall_Double
import godot.icalls._icall_Unit_Double
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any
import kotlin.Double

open class PinJoint2D internal constructor(
  _ignore: Any?
) : Joint2D(_ignore) {
  open var softness: Double
    get() {
      val mb = getMethodBind("PinJoint2D","get_softness")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PinJoint2D","set_softness")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("PinJoint2D", "PinJoint2D")
        }

  }

  open fun getSoftness(): Double {
    val mb = getMethodBind("PinJoint2D","get_softness")
    return _icall_Double( mb, this.ptr)
  }

  open fun setSoftness(softness: Double) {
    val mb = getMethodBind("PinJoint2D","set_softness")
    _icall_Unit_Double( mb, this.ptr, softness)
  }

  companion object
}