// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.core.RID
import godot.core.Signal0
import godot.core.signal
import godot.icalls._icall_Bone2D_Long
import godot.icalls._icall_Long
import godot.icalls._icall_RID
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Long
import kotlinx.cinterop.COpaquePointer

open class Skeleton2D : Node2D() {
  val boneSetupChanged: Signal0 by signal()

  override fun __new(): COpaquePointer = invokeConstructor("Skeleton2D", "Skeleton2D")

  open fun _updateBoneSetup() {
  }

  open fun _updateTransform() {
  }

  open fun getBone(idx: Long): Bone2D {
    val mb = getMethodBind("Skeleton2D","get_bone")
    return _icall_Bone2D_Long( mb, this.ptr, idx)
  }

  open fun getBoneCount(): Long {
    val mb = getMethodBind("Skeleton2D","get_bone_count")
    return _icall_Long( mb, this.ptr)
  }

  open fun getSkeleton(): RID {
    val mb = getMethodBind("Skeleton2D","get_skeleton")
    return _icall_RID( mb, this.ptr)
  }
}
