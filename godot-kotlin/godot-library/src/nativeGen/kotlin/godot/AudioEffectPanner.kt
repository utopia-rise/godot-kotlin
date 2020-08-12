// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.core.Godot.shouldInitPtr
import godot.icalls._icall_Double
import godot.icalls._icall_Unit_Double
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Any
import kotlin.Double

open class AudioEffectPanner internal constructor(
  _ignore: Any?
) : AudioEffect(_ignore) {
  open var pan: Double
    get() {
      val mb = getMethodBind("AudioEffectPanner","get_pan")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectPanner","set_pan")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = invokeConstructor("AudioEffectPanner", "AudioEffectPanner")
        }

  }

  open fun getPan(): Double {
    val mb = getMethodBind("AudioEffectPanner","get_pan")
    return _icall_Double( mb, this.ptr)
  }

  open fun setPan(cpanume: Double) {
    val mb = getMethodBind("AudioEffectPanner","set_pan")
    _icall_Unit_Double( mb, this.ptr, cpanume)
  }
}
