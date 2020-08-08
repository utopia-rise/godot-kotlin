package godot

import godot.core.Godot.shouldInitPtr
import godot.core.PoolVector3Array
import godot.icalls._icall_PoolVector3Array
import godot.icalls._icall_Unit_PoolVector3Array
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any

open class ConvexPolygonShape internal constructor(
  _ignore: Any?
) : Shape(_ignore) {
  open var points: PoolVector3Array
    get() {
      val mb = getMethodBind("ConvexPolygonShape","get_points")
      return _icall_PoolVector3Array(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("ConvexPolygonShape","set_points")
      _icall_Unit_PoolVector3Array(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("ConvexPolygonShape", "ConvexPolygonShape")
        }

  }

  open fun getPoints(): PoolVector3Array {
    val mb = getMethodBind("ConvexPolygonShape","get_points")
    return _icall_PoolVector3Array( mb, this.ptr)
  }

  open fun setPoints(points: PoolVector3Array) {
    val mb = getMethodBind("ConvexPolygonShape","set_points")
    _icall_Unit_PoolVector3Array( mb, this.ptr, points)
  }

  companion object
}