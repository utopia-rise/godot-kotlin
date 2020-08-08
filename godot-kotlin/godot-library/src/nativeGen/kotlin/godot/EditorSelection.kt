package godot

import godot.core.Signal0
import godot.core.VariantArray
import godot.core.signal
import godot.icalls._icall_Unit
import godot.icalls._icall_Unit_Object
import godot.icalls._icall_VariantArray
import godot.internal.utils.getMethodBind
import kotlin.Any

open class EditorSelection internal constructor(
  _ignore: Any?
) : Object(_ignore) {
  val selectionChanged: Signal0 by signal()

  internal constructor() : this(null)

  open fun _emit_change() {
  }

  open fun _node_removed(arg0: Node) {
  }

  open fun addNode(node: Node) {
    val mb = getMethodBind("EditorSelection","add_node")
    _icall_Unit_Object( mb, this.ptr, node)
  }

  open fun clear() {
    val mb = getMethodBind("EditorSelection","clear")
    _icall_Unit( mb, this.ptr)
  }

  open fun getSelectedNodes(): VariantArray {
    val mb = getMethodBind("EditorSelection","get_selected_nodes")
    return _icall_VariantArray( mb, this.ptr)
  }

  open fun getTransformableSelectedNodes(): VariantArray {
    val mb = getMethodBind("EditorSelection","get_transformable_selected_nodes")
    return _icall_VariantArray( mb, this.ptr)
  }

  open fun removeNode(node: Node) {
    val mb = getMethodBind("EditorSelection","remove_node")
    _icall_Unit_Object( mb, this.ptr, node)
  }

  companion object
}