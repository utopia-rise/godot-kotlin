package godot

import godot.core.Godot.shouldInitPtr
import godot.core.VariantArray
import godot.icalls._icall_BaseButton
import godot.icalls._icall_VariantArray
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any

open class ButtonGroup internal constructor(
  _ignore: Any?
) : Resource(_ignore) {
  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("ButtonGroup", "ButtonGroup")
        }

  }

  open fun getButtons(): VariantArray {
    val mb = getMethodBind("ButtonGroup","get_buttons")
    return _icall_VariantArray( mb, this.ptr)
  }

  open fun getPressedButton(): BaseButton {
    val mb = getMethodBind("ButtonGroup","get_pressed_button")
    return _icall_BaseButton( mb, this.ptr)
  }

  companion object
}