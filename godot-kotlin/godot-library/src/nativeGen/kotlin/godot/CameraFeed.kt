// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.CameraFeed
import godot.core.Transform2D
import godot.icalls._icall_Boolean
import godot.icalls._icall_Long
import godot.icalls._icall_String
import godot.icalls._icall_Transform2D
import godot.icalls._icall_Unit_Boolean
import godot.icalls._icall_Unit_Transform2D
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlinx.cinterop.COpaquePointer

open class CameraFeed : Reference() {
  open var feedIsActive: Boolean
    get() {
      val mb = getMethodBind("CameraFeed","is_active")
      return _icall_Boolean(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("CameraFeed","set_active")
      _icall_Unit_Boolean(mb, this.ptr, value)
    }

  open var feedTransform: Transform2D
    get() {
      val mb = getMethodBind("CameraFeed","get_transform")
      return _icall_Transform2D(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("CameraFeed","set_transform")
      _icall_Unit_Transform2D(mb, this.ptr, value)
    }

  override fun __new(): COpaquePointer = invokeConstructor("CameraFeed", "CameraFeed")

  open fun feedTransform(schedule: Transform2D.() -> Unit): Transform2D = feedTransform.apply{
      schedule(this)
      feedTransform = this
  }


  open fun _allocateTexture(
    width: Long,
    height: Long,
    format: Long,
    textureType: Long,
    dataType: Long
  ) {
  }

  open fun _setRGBImg(rgbImg: Image) {
  }

  open fun _setYCbCrImg(ycbcrImg: Image) {
  }

  open fun _setYCbCrImgs(yImg: Image, cbcrImg: Image) {
  }

  open fun _setName(name: String) {
  }

  open fun _setPosition(position: Long) {
  }

  open fun getId(): Long {
    val mb = getMethodBind("CameraFeed","get_id")
    return _icall_Long( mb, this.ptr)
  }

  open fun getName(): String {
    val mb = getMethodBind("CameraFeed","get_name")
    return _icall_String( mb, this.ptr)
  }

  open fun getPosition(): CameraFeed.FeedPosition {
    val mb = getMethodBind("CameraFeed","get_position")
    return CameraFeed.FeedPosition.from( _icall_Long( mb, this.ptr))
  }

  open fun getTransform(): Transform2D {
    val mb = getMethodBind("CameraFeed","get_transform")
    return _icall_Transform2D( mb, this.ptr)
  }

  open fun isActive(): Boolean {
    val mb = getMethodBind("CameraFeed","is_active")
    return _icall_Boolean( mb, this.ptr)
  }

  open fun setActive(active: Boolean) {
    val mb = getMethodBind("CameraFeed","set_active")
    _icall_Unit_Boolean( mb, this.ptr, active)
  }

  open fun setTransform(transform: Transform2D) {
    val mb = getMethodBind("CameraFeed","set_transform")
    _icall_Unit_Transform2D( mb, this.ptr, transform)
  }

  enum class FeedDataType(
    id: Long
  ) {
    FEED_NOIMAGE(0),

    FEED_RGB(1),

    FEED_YCBCR(2),

    FEED_YCBCR_SEP(3);

    val id: Long
    init {
      this.id = id
    }

    companion object {
      fun from(value: Long) = values().single { it.id == value }
    }
  }

  enum class FeedPosition(
    id: Long
  ) {
    FEED_UNSPECIFIED(0),

    FEED_FRONT(1),

    FEED_BACK(2);

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
