package godot

import godot.core.Godot.shouldInitPtr
import godot.core.Variant
import godot.internal.utils.getConstructor
import kotlin.Any
import kotlin.NotImplementedError

open class VisualScriptSubCall internal constructor(
  _ignore: Any?
) : VisualScriptNode(_ignore) {
  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("VisualScriptSubCall", "VisualScriptSubCall")
        }

  }

  open fun _subcall(arguments: Variant): Variant {
    throw NotImplementedError("_subcall is not implemented for VisualScriptSubCall")
  }

  companion object
}
