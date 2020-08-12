// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.core.Godot.shouldInitPtr
import godot.icalls._icall_String
import godot.icalls._icall_Unit_String
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Any
import kotlin.String

open class VisualScriptEngineSingleton internal constructor(
  _ignore: Any?
) : VisualScriptNode(_ignore) {
  open var constant: String
    get() {
      val mb = getMethodBind("VisualScriptEngineSingleton","get_singleton")
      return _icall_String(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("VisualScriptEngineSingleton","set_singleton")
      _icall_Unit_String(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = invokeConstructor("VisualScriptEngineSingleton",
            "VisualScriptEngineSingleton")
        }

  }

  open fun getSingleton(): String {
    val mb = getMethodBind("VisualScriptEngineSingleton","get_singleton")
    return _icall_String( mb, this.ptr)
  }

  open fun setSingleton(name: String) {
    val mb = getMethodBind("VisualScriptEngineSingleton","set_singleton")
    _icall_Unit_String( mb, this.ptr, name)
  }
}
