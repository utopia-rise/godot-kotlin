// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.icalls._icall_Long
import godot.icalls._icall_Unit_Long
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Long
import kotlinx.cinterop.COpaquePointer

open class EncodedObjectAsID : Reference() {
  open var objectId: Long
    get() {
      val mb = getMethodBind("EncodedObjectAsID","get_object_id")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("EncodedObjectAsID","set_object_id")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  override fun __new(): COpaquePointer = invokeConstructor("EncodedObjectAsID", "EncodedObjectAsID")

  open fun getObjectId(): Long {
    val mb = getMethodBind("EncodedObjectAsID","get_object_id")
    return _icall_Long( mb, this.ptr)
  }

  open fun setObjectId(id: Long) {
    val mb = getMethodBind("EncodedObjectAsID","set_object_id")
    _icall_Unit_Long( mb, this.ptr, id)
  }
}
