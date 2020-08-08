package godot

import godot.core.Godot.shouldInitPtr
import godot.icalls._icall_Boolean
import godot.icalls._icall_Unit_Boolean
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any
import kotlin.Boolean

open class ProgressBar internal constructor(
  _ignore: Any?
) : Range(_ignore) {
  open var percentVisible: Boolean
    get() {
      val mb = getMethodBind("ProgressBar","is_percent_visible")
      return _icall_Boolean(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("ProgressBar","set_percent_visible")
      _icall_Unit_Boolean(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("ProgressBar", "ProgressBar")
        }

  }

  open fun isPercentVisible(): Boolean {
    val mb = getMethodBind("ProgressBar","is_percent_visible")
    return _icall_Boolean( mb, this.ptr)
  }

  open fun setPercentVisible(visible: Boolean) {
    val mb = getMethodBind("ProgressBar","set_percent_visible")
    _icall_Unit_Boolean( mb, this.ptr, visible)
  }

  companion object
}