// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.core.Godot.shouldInitPtr
import godot.internal.utils.getConstructor
import kotlin.Any
import kotlin.Double
import kotlin.NotImplementedError

open class Position2D internal constructor(
  _ignore: Any?
) : Node2D(_ignore) {
  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("Position2D", "Position2D")
        }

  }

  open fun _get_gizmo_extents(): Double {
    throw NotImplementedError("_get_gizmo_extents is not implemented for Position2D")
  }

  open fun _set_gizmo_extents(extents: Double) {
  }
}
