package godot

import godot.core.Godot.shouldInitPtr
import godot.internal.utils.getConstructor
import kotlin.Any

open class VisualScriptSelf internal constructor(
  _ignore: Any?
) : VisualScriptNode(_ignore) {
  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("VisualScriptSelf", "VisualScriptSelf")
        }

  }

  companion object
}