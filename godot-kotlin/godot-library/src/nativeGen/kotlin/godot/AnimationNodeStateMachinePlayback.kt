// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.core.PoolStringArray
import godot.icalls._icall_Boolean
import godot.icalls._icall_PoolStringArray
import godot.icalls._icall_String
import godot.icalls._icall_Unit
import godot.icalls._icall_Unit_String
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Boolean
import kotlin.String
import kotlinx.cinterop.COpaquePointer

open class AnimationNodeStateMachinePlayback : Resource() {
  override fun __new(): COpaquePointer = invokeConstructor("AnimationNodeStateMachinePlayback",
      "AnimationNodeStateMachinePlayback")

  open fun getCurrentNode(): String {
    val mb = getMethodBind("AnimationNodeStateMachinePlayback","get_current_node")
    return _icall_String( mb, this.ptr)
  }

  open fun getTravelPath(): PoolStringArray {
    val mb = getMethodBind("AnimationNodeStateMachinePlayback","get_travel_path")
    return _icall_PoolStringArray( mb, this.ptr)
  }

  open fun isPlaying(): Boolean {
    val mb = getMethodBind("AnimationNodeStateMachinePlayback","is_playing")
    return _icall_Boolean( mb, this.ptr)
  }

  open fun start(node: String) {
    val mb = getMethodBind("AnimationNodeStateMachinePlayback","start")
    _icall_Unit_String( mb, this.ptr, node)
  }

  open fun stop() {
    val mb = getMethodBind("AnimationNodeStateMachinePlayback","stop")
    _icall_Unit( mb, this.ptr)
  }

  open fun travel(toNode: String) {
    val mb = getMethodBind("AnimationNodeStateMachinePlayback","travel")
    _icall_Unit_String( mb, this.ptr, toNode)
  }
}
