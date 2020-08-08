package godot

import godot.core.Godot.shouldInitPtr
import godot.internal.utils.getConstructor
import kotlin.Any

open class AnimationNodeOutput internal constructor(
  _ignore: Any?
) : AnimationNode(_ignore) {
  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("AnimationNodeOutput", "AnimationNodeOutput")
        }

  }

  companion object
}
