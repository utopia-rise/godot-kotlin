package godot

import godot.core.Godot.shouldInitPtr
import godot.internal.utils.getConstructor
import kotlin.Any

open class VisualShaderNodeTextureUniformTriplanar internal constructor(
  _ignore: Any?
) : VisualShaderNodeTextureUniform(_ignore) {
  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("VisualShaderNodeTextureUniformTriplanar",
            "VisualShaderNodeTextureUniformTriplanar")
        }

  }

  companion object
}