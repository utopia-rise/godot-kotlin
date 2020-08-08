package godot

import godot.core.Godot.shouldInitPtr
import godot.core.Vector2
import godot.icalls._icall_Double
import godot.icalls._icall_Unit_Double
import godot.icalls._icall_Unit_Vector2
import godot.icalls._icall_Vector2
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any
import kotlin.Double
import kotlin.Unit

open class InputEventMouseMotion internal constructor(
  _ignore: Any?
) : InputEventMouse(_ignore) {
  open var pressure: Double
    get() {
      val mb = getMethodBind("InputEventMouseMotion","get_pressure")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("InputEventMouseMotion","set_pressure")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var relative: Vector2
    get() {
      val mb = getMethodBind("InputEventMouseMotion","get_relative")
      return _icall_Vector2(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("InputEventMouseMotion","set_relative")
      _icall_Unit_Vector2(mb, this.ptr, value)
    }

  open var speed: Vector2
    get() {
      val mb = getMethodBind("InputEventMouseMotion","get_speed")
      return _icall_Vector2(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("InputEventMouseMotion","set_speed")
      _icall_Unit_Vector2(mb, this.ptr, value)
    }

  open var tilt: Vector2
    get() {
      val mb = getMethodBind("InputEventMouseMotion","get_tilt")
      return _icall_Vector2(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("InputEventMouseMotion","set_tilt")
      _icall_Unit_Vector2(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("InputEventMouseMotion", "InputEventMouseMotion")
        }

  }

  open fun relative(schedule: Vector2.() -> Unit): Vector2 = relative.apply{
      schedule(this)
      relative = this
  }


  open fun speed(schedule: Vector2.() -> Unit): Vector2 = speed.apply{
      schedule(this)
      speed = this
  }


  open fun tilt(schedule: Vector2.() -> Unit): Vector2 = tilt.apply{
      schedule(this)
      tilt = this
  }


  open fun getPressure(): Double {
    val mb = getMethodBind("InputEventMouseMotion","get_pressure")
    return _icall_Double( mb, this.ptr)
  }

  open fun getRelative(): Vector2 {
    val mb = getMethodBind("InputEventMouseMotion","get_relative")
    return _icall_Vector2( mb, this.ptr)
  }

  open fun getSpeed(): Vector2 {
    val mb = getMethodBind("InputEventMouseMotion","get_speed")
    return _icall_Vector2( mb, this.ptr)
  }

  open fun getTilt(): Vector2 {
    val mb = getMethodBind("InputEventMouseMotion","get_tilt")
    return _icall_Vector2( mb, this.ptr)
  }

  open fun setPressure(pressure: Double) {
    val mb = getMethodBind("InputEventMouseMotion","set_pressure")
    _icall_Unit_Double( mb, this.ptr, pressure)
  }

  open fun setRelative(relative: Vector2) {
    val mb = getMethodBind("InputEventMouseMotion","set_relative")
    _icall_Unit_Vector2( mb, this.ptr, relative)
  }

  open fun setSpeed(speed: Vector2) {
    val mb = getMethodBind("InputEventMouseMotion","set_speed")
    _icall_Unit_Vector2( mb, this.ptr, speed)
  }

  open fun setTilt(tilt: Vector2) {
    val mb = getMethodBind("InputEventMouseMotion","set_tilt")
    _icall_Unit_Vector2( mb, this.ptr, tilt)
  }

  companion object
}
