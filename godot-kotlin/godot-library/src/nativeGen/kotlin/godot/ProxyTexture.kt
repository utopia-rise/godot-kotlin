// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.core.Godot.shouldInitPtr
import godot.icalls._icall_Texture
import godot.icalls._icall_Unit_Object
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Any

open class ProxyTexture internal constructor(
  _ignore: Any?
) : Texture(_ignore) {
  open var base: Texture
    get() {
      val mb = getMethodBind("ProxyTexture","get_base")
      return _icall_Texture(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("ProxyTexture","set_base")
      _icall_Unit_Object(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = invokeConstructor("ProxyTexture", "ProxyTexture")
        }

  }

  open fun getBase(): Texture {
    val mb = getMethodBind("ProxyTexture","get_base")
    return _icall_Texture( mb, this.ptr)
  }

  open fun setBase(base: Texture) {
    val mb = getMethodBind("ProxyTexture","set_base")
    _icall_Unit_Object( mb, this.ptr, base)
  }
}
