package godot

import godot.core.Godot.shouldInitPtr
import godot.core.GodotError
import godot.core.PoolByteArray
import godot.core.PoolIntArray
import godot.core.Signal0
import godot.core.Signal1
import godot.core.Signal2
import godot.core.signal
import godot.icalls._icall_Boolean
import godot.icalls._icall_Long
import godot.icalls._icall_Long_PoolByteArray_Long_Long
import godot.icalls._icall_NetworkedMultiplayerPeer
import godot.icalls._icall_PoolIntArray
import godot.icalls._icall_Unit
import godot.icalls._icall_Unit_Boolean
import godot.icalls._icall_Unit_Object
import godot.internal.utils.getConstructor
import godot.internal.utils.getMethodBind
import kotlin.Any
import kotlin.Boolean
import kotlin.Long

open class MultiplayerAPI internal constructor(
  _ignore: Any?
) : Reference(_ignore) {
  val connectedToServer: Signal0 by signal()

  val connectionFailed: Signal0 by signal()

  val networkPeerConnected: Signal1<Long> by signal("id")

  val networkPeerDisconnected: Signal1<Long> by signal("id")

  val networkPeerPacket: Signal2<Long, PoolByteArray> by signal("id", "packet")

  val serverDisconnected: Signal0 by signal()

  open var allowObjectDecoding: Boolean
    get() {
      val mb = getMethodBind("MultiplayerAPI","is_object_decoding_allowed")
      return _icall_Boolean(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("MultiplayerAPI","set_allow_object_decoding")
      _icall_Unit_Boolean(mb, this.ptr, value)
    }

  open var networkPeer: NetworkedMultiplayerPeer
    get() {
      val mb = getMethodBind("MultiplayerAPI","get_network_peer")
      return _icall_NetworkedMultiplayerPeer(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("MultiplayerAPI","set_network_peer")
      _icall_Unit_Object(mb, this.ptr, value)
    }

  open var refuseNewNetworkConnections: Boolean
    get() {
      val mb = getMethodBind("MultiplayerAPI","is_refusing_new_network_connections")
      return _icall_Boolean(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("MultiplayerAPI","set_refuse_new_network_connections")
      _icall_Unit_Boolean(mb, this.ptr, value)
    }

  constructor() : this(null) {
    if (shouldInitPtr()) {
            this.ptr = getConstructor("MultiplayerAPI", "MultiplayerAPI")
        }

  }

  open fun _add_peer(id: Long) {
  }

  open fun _connected_to_server() {
  }

  open fun _connection_failed() {
  }

  open fun _del_peer(id: Long) {
  }

  open fun _server_disconnected() {
  }

  open fun clear() {
    val mb = getMethodBind("MultiplayerAPI","clear")
    _icall_Unit( mb, this.ptr)
  }

  open fun getNetworkConnectedPeers(): PoolIntArray {
    val mb = getMethodBind("MultiplayerAPI","get_network_connected_peers")
    return _icall_PoolIntArray( mb, this.ptr)
  }

  open fun getNetworkPeer(): NetworkedMultiplayerPeer {
    val mb = getMethodBind("MultiplayerAPI","get_network_peer")
    return _icall_NetworkedMultiplayerPeer( mb, this.ptr)
  }

  open fun getNetworkUniqueId(): Long {
    val mb = getMethodBind("MultiplayerAPI","get_network_unique_id")
    return _icall_Long( mb, this.ptr)
  }

  open fun getRpcSenderId(): Long {
    val mb = getMethodBind("MultiplayerAPI","get_rpc_sender_id")
    return _icall_Long( mb, this.ptr)
  }

  open fun hasNetworkPeer(): Boolean {
    val mb = getMethodBind("MultiplayerAPI","has_network_peer")
    return _icall_Boolean( mb, this.ptr)
  }

  open fun isNetworkServer(): Boolean {
    val mb = getMethodBind("MultiplayerAPI","is_network_server")
    return _icall_Boolean( mb, this.ptr)
  }

  open fun isObjectDecodingAllowed(): Boolean {
    val mb = getMethodBind("MultiplayerAPI","is_object_decoding_allowed")
    return _icall_Boolean( mb, this.ptr)
  }

  open fun isRefusingNewNetworkConnections(): Boolean {
    val mb = getMethodBind("MultiplayerAPI","is_refusing_new_network_connections")
    return _icall_Boolean( mb, this.ptr)
  }

  open fun poll() {
    val mb = getMethodBind("MultiplayerAPI","poll")
    _icall_Unit( mb, this.ptr)
  }

  open fun sendBytes(
    bytes: PoolByteArray,
    id: Long = 0,
    mode: Long = 2
  ): GodotError {
    val mb = getMethodBind("MultiplayerAPI","send_bytes")
    return GodotError.byValue( _icall_Long_PoolByteArray_Long_Long( mb, this.ptr, bytes, id,
        mode).toUInt())
  }

  open fun setAllowObjectDecoding(enable: Boolean) {
    val mb = getMethodBind("MultiplayerAPI","set_allow_object_decoding")
    _icall_Unit_Boolean( mb, this.ptr, enable)
  }

  open fun setNetworkPeer(peer: NetworkedMultiplayerPeer) {
    val mb = getMethodBind("MultiplayerAPI","set_network_peer")
    _icall_Unit_Object( mb, this.ptr, peer)
  }

  open fun setRefuseNewNetworkConnections(refuse: Boolean) {
    val mb = getMethodBind("MultiplayerAPI","set_refuse_new_network_connections")
    _icall_Unit_Boolean( mb, this.ptr, refuse)
  }

  open fun setRootNode(node: Node) {
    val mb = getMethodBind("MultiplayerAPI","set_root_node")
    _icall_Unit_Object( mb, this.ptr, node)
  }

  enum class RPCMode(
    id: Long
  ) {
    RPC_MODE_DISABLED(0),

    RPC_MODE_REMOTE(1),

    RPC_MODE_MASTER(2),

    RPC_MODE_PUPPET(3),

    RPC_MODE_SLAVE(3),

    RPC_MODE_REMOTESYNC(4),

    RPC_MODE_SYNC(4),

    RPC_MODE_MASTERSYNC(5),

    RPC_MODE_PUPPETSYNC(6);

    val id: Long
    init {
      this.id = id
    }

    companion object {
      fun from(value: Long) = values().single { it.id == value }
    }
  }

  companion object {
    final const val RPC_MODE_DISABLED: Long = 0

    final const val RPC_MODE_MASTER: Long = 2

    final const val RPC_MODE_MASTERSYNC: Long = 5

    final const val RPC_MODE_PUPPET: Long = 3

    final const val RPC_MODE_PUPPETSYNC: Long = 6

    final const val RPC_MODE_REMOTE: Long = 1

    final const val RPC_MODE_REMOTESYNC: Long = 4

    final const val RPC_MODE_SLAVE: Long = 3

    final const val RPC_MODE_SYNC: Long = 4
  }
}
