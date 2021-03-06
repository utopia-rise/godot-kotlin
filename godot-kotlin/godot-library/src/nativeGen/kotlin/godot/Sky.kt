// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.Sky
import godot.icalls._icall_Long
import godot.icalls._icall_Unit_Long
import godot.internal.utils.getMethodBind
import kotlin.Long

open class Sky internal constructor() : Resource() {
  open var radianceSize: Long
    get() {
      val mb = getMethodBind("Sky","get_radiance_size")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("Sky","set_radiance_size")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  open fun getRadianceSize(): Sky.RadianceSize {
    val mb = getMethodBind("Sky","get_radiance_size")
    return Sky.RadianceSize.from( _icall_Long( mb, this.ptr))
  }

  open fun setRadianceSize(size: Long) {
    val mb = getMethodBind("Sky","set_radiance_size")
    _icall_Unit_Long( mb, this.ptr, size)
  }

  enum class RadianceSize(
    id: Long
  ) {
    RADIANCE_SIZE_32(0),

    RADIANCE_SIZE_64(1),

    RADIANCE_SIZE_128(2),

    RADIANCE_SIZE_256(3),

    RADIANCE_SIZE_512(4),

    RADIANCE_SIZE_1024(5),

    RADIANCE_SIZE_2048(6),

    RADIANCE_SIZE_MAX(7);

    val id: Long
    init {
      this.id = id
    }

    companion object {
      fun from(value: Long) = values().single { it.id == value }
    }
  }

  companion object
}
