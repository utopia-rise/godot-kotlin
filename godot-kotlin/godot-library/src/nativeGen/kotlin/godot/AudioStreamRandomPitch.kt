// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.icalls._icall_AudioStream
import godot.icalls._icall_Double
import godot.icalls._icall_Unit_Double
import godot.icalls._icall_Unit_Object
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Double
import kotlinx.cinterop.COpaquePointer

open class AudioStreamRandomPitch : AudioStream() {
  open var audioStream: AudioStream
    get() {
      val mb = getMethodBind("AudioStreamRandomPitch","get_audio_stream")
      return _icall_AudioStream(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioStreamRandomPitch","set_audio_stream")
      _icall_Unit_Object(mb, this.ptr, value)
    }

  open var randomPitch: Double
    get() {
      val mb = getMethodBind("AudioStreamRandomPitch","get_random_pitch")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioStreamRandomPitch","set_random_pitch")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  override fun __new(): COpaquePointer = invokeConstructor("AudioStreamRandomPitch",
      "AudioStreamRandomPitch")

  open fun getAudioStream(): AudioStream {
    val mb = getMethodBind("AudioStreamRandomPitch","get_audio_stream")
    return _icall_AudioStream( mb, this.ptr)
  }

  open fun getRandomPitch(): Double {
    val mb = getMethodBind("AudioStreamRandomPitch","get_random_pitch")
    return _icall_Double( mb, this.ptr)
  }

  open fun setAudioStream(stream: AudioStream) {
    val mb = getMethodBind("AudioStreamRandomPitch","set_audio_stream")
    _icall_Unit_Object( mb, this.ptr, stream)
  }

  open fun setRandomPitch(scale: Double) {
    val mb = getMethodBind("AudioStreamRandomPitch","set_random_pitch")
    _icall_Unit_Double( mb, this.ptr, scale)
  }
}
