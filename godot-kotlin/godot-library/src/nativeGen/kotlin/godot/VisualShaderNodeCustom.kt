// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.core.Godot.shouldInitPtr
import godot.core.VariantArray
import godot.internal.utils.invokeConstructor
import kotlin.Any
import kotlin.Long
import kotlin.NotImplementedError
import kotlin.String

open class VisualShaderNodeCustom internal constructor(
  _ignore: Any?
) : VisualShaderNode(_ignore) {
  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = invokeConstructor("VisualShaderNodeCustom", "VisualShaderNodeCustom")
        }

  }

  open fun _getCategory(): String {
    throw NotImplementedError("_get_category is not implemented for VisualShaderNodeCustom")
  }

  open fun _getCode(
    inputVars: VariantArray,
    outputVars: VariantArray,
    mode: Long,
    type: Long
  ): String {
    throw NotImplementedError("_get_code is not implemented for VisualShaderNodeCustom")
  }

  open fun _getDescription(): String {
    throw NotImplementedError("_get_description is not implemented for VisualShaderNodeCustom")
  }

  open fun _getGlobalCode(mode: Long): String {
    throw NotImplementedError("_get_global_code is not implemented for VisualShaderNodeCustom")
  }

  open fun _getInputPortCount(): Long {
    throw NotImplementedError("_get_input_port_count is not implemented for VisualShaderNodeCustom")
  }

  open fun _getInputPortName(port: Long): String {
    throw NotImplementedError("_get_input_port_name is not implemented for VisualShaderNodeCustom")
  }

  open fun _getInputPortType(port: Long): Long {
    throw NotImplementedError("_get_input_port_type is not implemented for VisualShaderNodeCustom")
  }

  open fun _getName(): String {
    throw NotImplementedError("_get_name is not implemented for VisualShaderNodeCustom")
  }

  open fun _getOutputPortCount(): Long {
    throw
        NotImplementedError("_get_output_port_count is not implemented for VisualShaderNodeCustom")
  }

  open fun _getOutputPortName(port: Long): String {
    throw NotImplementedError("_get_output_port_name is not implemented for VisualShaderNodeCustom")
  }

  open fun _getOutputPortType(port: Long): Long {
    throw NotImplementedError("_get_output_port_type is not implemented for VisualShaderNodeCustom")
  }

  open fun _getReturnIconType(): Long {
    throw NotImplementedError("_get_return_icon_type is not implemented for VisualShaderNodeCustom")
  }

  open fun _getSubcategory(): String {
    throw NotImplementedError("_get_subcategory is not implemented for VisualShaderNodeCustom")
  }
}
