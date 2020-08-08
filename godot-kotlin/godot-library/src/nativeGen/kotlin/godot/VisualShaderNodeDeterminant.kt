package godot

import godot.core.Godot.shouldInitPtr
import godot.internal.utils.getConstructor
import kotlin.Any

open class VisualShaderNodeDeterminant internal constructor(
  _ignore: Any?
) : VisualShaderNode(_ignore) {
  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("VisualShaderNodeDeterminant", "VisualShaderNodeDeterminant")
        }

  }

  companion object
}