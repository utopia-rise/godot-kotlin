// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.icalls._icall_EditorInterface
import godot.icalls._icall_Node
import godot.icalls._icall_Unit_Object
import godot.internal.utils.getMethodBind

open class EditorScript internal constructor() : Reference() {
  open fun _run() {
  }

  open fun addRootNode(node: Node) {
    val mb = getMethodBind("EditorScript","add_root_node")
    _icall_Unit_Object( mb, this.ptr, node)
  }

  open fun getEditorInterface(): EditorInterface {
    val mb = getMethodBind("EditorScript","get_editor_interface")
    return _icall_EditorInterface( mb, this.ptr)
  }

  open fun getScene(): Node {
    val mb = getMethodBind("EditorScript","get_scene")
    return _icall_Node( mb, this.ptr)
  }
}
