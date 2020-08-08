package godot

import godot.CubeMap
import godot.core.Godot.shouldInitPtr
import godot.icalls._icall_Double
import godot.icalls._icall_Image_Long
import godot.icalls._icall_Long
import godot.icalls._icall_Unit_Double
import godot.icalls._icall_Unit_Long
import godot.icalls._icall_Unit_Long_Object
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any
import kotlin.Double
import kotlin.Long

open class CubeMap internal constructor(
  _ignore: Any?
) : Resource(_ignore) {
  open var flags: Long
    get() {
      val mb = getMethodBind("CubeMap","get_flags")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("CubeMap","set_flags")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  open var lossyStorageQuality: Double
    get() {
      val mb = getMethodBind("CubeMap","get_lossy_storage_quality")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("CubeMap","set_lossy_storage_quality")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var storageMode: Long
    get() {
      val mb = getMethodBind("CubeMap","get_storage")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("CubeMap","set_storage")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("CubeMap", "CubeMap")
        }

  }

  open fun getFlags(): Long {
    val mb = getMethodBind("CubeMap","get_flags")
    return _icall_Long( mb, this.ptr)
  }

  open fun getHeight(): Long {
    val mb = getMethodBind("CubeMap","get_height")
    return _icall_Long( mb, this.ptr)
  }

  open fun getLossyStorageQuality(): Double {
    val mb = getMethodBind("CubeMap","get_lossy_storage_quality")
    return _icall_Double( mb, this.ptr)
  }

  open fun getSide(side: Long): Image {
    val mb = getMethodBind("CubeMap","get_side")
    return _icall_Image_Long( mb, this.ptr, side)
  }

  open fun getStorage(): CubeMap.Storage {
    val mb = getMethodBind("CubeMap","get_storage")
    return CubeMap.Storage.from( _icall_Long( mb, this.ptr))
  }

  open fun getWidth(): Long {
    val mb = getMethodBind("CubeMap","get_width")
    return _icall_Long( mb, this.ptr)
  }

  open fun setFlags(flags: Long) {
    val mb = getMethodBind("CubeMap","set_flags")
    _icall_Unit_Long( mb, this.ptr, flags)
  }

  open fun setLossyStorageQuality(quality: Double) {
    val mb = getMethodBind("CubeMap","set_lossy_storage_quality")
    _icall_Unit_Double( mb, this.ptr, quality)
  }

  open fun setSide(side: Long, image: Image) {
    val mb = getMethodBind("CubeMap","set_side")
    _icall_Unit_Long_Object( mb, this.ptr, side, image)
  }

  open fun setStorage(mode: Long) {
    val mb = getMethodBind("CubeMap","set_storage")
    _icall_Unit_Long( mb, this.ptr, mode)
  }

  enum class Flags(
    id: Long
  ) {
    FLAG_MIPMAPS(1),

    FLAG_REPEAT(2),

    FLAG_FILTER(4),

    FLAGS_DEFAULT(7);

    val id: Long
    init {
      this.id = id
    }

    companion object {
      fun from(value: Long) = values().single { it.id == value }
    }
  }

  enum class Side(
    id: Long
  ) {
    SIDE_LEFT(0),

    SIDE_RIGHT(1),

    SIDE_BOTTOM(2),

    SIDE_TOP(3),

    SIDE_FRONT(4),

    SIDE_BACK(5);

    val id: Long
    init {
      this.id = id
    }

    companion object {
      fun from(value: Long) = values().single { it.id == value }
    }
  }

  enum class Storage(
    id: Long
  ) {
    STORAGE_RAW(0),

    STORAGE_COMPRESS_LOSSY(1),

    STORAGE_COMPRESS_LOSSLESS(2);

    val id: Long
    init {
      this.id = id
    }

    companion object {
      fun from(value: Long) = values().single { it.id == value }
    }
  }

  companion object {
    final const val FLAGS_DEFAULT: Long = 7

    final const val FLAG_FILTER: Long = 4

    final const val FLAG_MIPMAPS: Long = 1

    final const val FLAG_REPEAT: Long = 2

    final const val SIDE_BACK: Long = 5

    final const val SIDE_BOTTOM: Long = 2

    final const val SIDE_FRONT: Long = 4

    final const val SIDE_LEFT: Long = 0

    final const val SIDE_RIGHT: Long = 1

    final const val SIDE_TOP: Long = 3

    final const val STORAGE_COMPRESS_LOSSLESS: Long = 2

    final const val STORAGE_COMPRESS_LOSSY: Long = 1

    final const val STORAGE_RAW: Long = 0
  }
}