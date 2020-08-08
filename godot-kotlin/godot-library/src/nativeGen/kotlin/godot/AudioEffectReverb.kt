package godot

import godot.core.Godot.shouldInitPtr
import godot.icalls._icall_Double
import godot.icalls._icall_Unit_Double
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any
import kotlin.Double

open class AudioEffectReverb internal constructor(
  _ignore: Any?
) : AudioEffect(_ignore) {
  open var damping: Double
    get() {
      val mb = getMethodBind("AudioEffectReverb","get_damping")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectReverb","set_damping")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var dry: Double
    get() {
      val mb = getMethodBind("AudioEffectReverb","get_dry")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectReverb","set_dry")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var hipass: Double
    get() {
      val mb = getMethodBind("AudioEffectReverb","get_hpf")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectReverb","set_hpf")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var predelayFeedback: Double
    get() {
      val mb = getMethodBind("AudioEffectReverb","get_predelay_feedback")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectReverb","set_predelay_feedback")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var predelayMsec: Double
    get() {
      val mb = getMethodBind("AudioEffectReverb","get_predelay_msec")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectReverb","set_predelay_msec")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var roomSize: Double
    get() {
      val mb = getMethodBind("AudioEffectReverb","get_room_size")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectReverb","set_room_size")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var spread: Double
    get() {
      val mb = getMethodBind("AudioEffectReverb","get_spread")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectReverb","set_spread")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var wet: Double
    get() {
      val mb = getMethodBind("AudioEffectReverb","get_wet")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectReverb","set_wet")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("AudioEffectReverb", "AudioEffectReverb")
        }

  }

  open fun getDamping(): Double {
    val mb = getMethodBind("AudioEffectReverb","get_damping")
    return _icall_Double( mb, this.ptr)
  }

  open fun getDry(): Double {
    val mb = getMethodBind("AudioEffectReverb","get_dry")
    return _icall_Double( mb, this.ptr)
  }

  open fun getHpf(): Double {
    val mb = getMethodBind("AudioEffectReverb","get_hpf")
    return _icall_Double( mb, this.ptr)
  }

  open fun getPredelayFeedback(): Double {
    val mb = getMethodBind("AudioEffectReverb","get_predelay_feedback")
    return _icall_Double( mb, this.ptr)
  }

  open fun getPredelayMsec(): Double {
    val mb = getMethodBind("AudioEffectReverb","get_predelay_msec")
    return _icall_Double( mb, this.ptr)
  }

  open fun getRoomSize(): Double {
    val mb = getMethodBind("AudioEffectReverb","get_room_size")
    return _icall_Double( mb, this.ptr)
  }

  open fun getSpread(): Double {
    val mb = getMethodBind("AudioEffectReverb","get_spread")
    return _icall_Double( mb, this.ptr)
  }

  open fun getWet(): Double {
    val mb = getMethodBind("AudioEffectReverb","get_wet")
    return _icall_Double( mb, this.ptr)
  }

  open fun setDamping(amount: Double) {
    val mb = getMethodBind("AudioEffectReverb","set_damping")
    _icall_Unit_Double( mb, this.ptr, amount)
  }

  open fun setDry(amount: Double) {
    val mb = getMethodBind("AudioEffectReverb","set_dry")
    _icall_Unit_Double( mb, this.ptr, amount)
  }

  open fun setHpf(amount: Double) {
    val mb = getMethodBind("AudioEffectReverb","set_hpf")
    _icall_Unit_Double( mb, this.ptr, amount)
  }

  open fun setPredelayFeedback(feedback: Double) {
    val mb = getMethodBind("AudioEffectReverb","set_predelay_feedback")
    _icall_Unit_Double( mb, this.ptr, feedback)
  }

  open fun setPredelayMsec(msec: Double) {
    val mb = getMethodBind("AudioEffectReverb","set_predelay_msec")
    _icall_Unit_Double( mb, this.ptr, msec)
  }

  open fun setRoomSize(size: Double) {
    val mb = getMethodBind("AudioEffectReverb","set_room_size")
    _icall_Unit_Double( mb, this.ptr, size)
  }

  open fun setSpread(amount: Double) {
    val mb = getMethodBind("AudioEffectReverb","set_spread")
    _icall_Unit_Double( mb, this.ptr, amount)
  }

  open fun setWet(amount: Double) {
    val mb = getMethodBind("AudioEffectReverb","set_wet")
    _icall_Unit_Double( mb, this.ptr, amount)
  }

  companion object
}
