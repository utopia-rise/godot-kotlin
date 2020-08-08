package godot

import godot.core.Godot.shouldInitPtr
import godot.icalls._icall_Button
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any

open class ConfirmationDialog internal constructor(
  _ignore: Any?
) : AcceptDialog(_ignore) {
  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("ConfirmationDialog", "ConfirmationDialog")
        }

  }

  open fun getCancel(): Button {
    val mb = getMethodBind("ConfirmationDialog","get_cancel")
    return _icall_Button( mb, this.ptr)
  }

  companion object
}