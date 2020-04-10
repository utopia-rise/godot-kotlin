package godot.core

import godot.gdnative.*
import godot.registration.RPCMode
import kotlinx.cinterop.*

internal class ClassHandle<T: Object>(
    private val nativescriptHandle: COpaquePointer,
    private val className: String,
    private val parentClassName: String,
    private val factory: () -> T,
    private val isTool: Boolean
) {
    private val disposables = mutableListOf<COpaquePointer>()

    fun wrap(instance: COpaquePointer): T {
        return Godot.noInitZone {
            factory().also { it.ptr = instance }
        }
    }

    fun init() {
        memScoped {
            val methodData = StableRef.create(this@ClassHandle).asCPointer()
            // register constructor and destructor
            val create = cValue<godot_instance_create_func> {
                create_func = staticCFunction(::createInstance)
                free_func = staticCFunction(::disposeClassHandle)
                method_data = methodData
            }
            val destroy = cValue<godot_instance_destroy_func> {
                destroy_func = staticCFunction(::destroyInstance)
                method_data = methodData
            }
            val registerMethod = if (isTool) {
                Godot.nativescript.godot_nativescript_register_tool_class
            } else {
                Godot.nativescript.godot_nativescript_register_class
            }
            checkNotNull(registerMethod)(
                nativescriptHandle,
                className.cstr.ptr,
                parentClassName.cstr.ptr,
                create,
                destroy
            )
        }
    }

    fun registerFunction(methodName: String, methodRef: COpaquePointer, rpcMode: RPCMode) {
        disposables.add(methodRef)
        memScoped {
            val attribs = cValue<godot_method_attributes> {
                rpc_type = when (rpcMode) {
                    RPCMode.DISABLED -> GODOT_METHOD_RPC_MODE_DISABLED
                    RPCMode.REMOTE -> GODOT_METHOD_RPC_MODE_REMOTE
                    RPCMode.SYNC -> GODOT_METHOD_RPC_MODE_SYNC
                    RPCMode.MASTER -> GODOT_METHOD_RPC_MODE_MASTER
                    RPCMode.SLAVE -> GODOT_METHOD_RPC_MODE_SLAVE
                    RPCMode.PUPPET -> GODOT_METHOD_RPC_MODE_PUPPET
                    RPCMode.REMOTE_SYNC -> GODOT_METHOD_RPC_MODE_REMOTESYNC
                    RPCMode.MASTER_SYNC -> GODOT_METHOD_RPC_MODE_MASTERSYNC
                    RPCMode.PUPPET_SYNC -> GODOT_METHOD_RPC_MODE_PUPPETSYNC
                    else -> throw AssertionError("Unsupported rpc mode $rpcMode")
                }
            }

            val instanceMethod = cValue<godot_instance_method> {
                method_data = methodRef
                this.method = staticCFunction(::invokeMethod)
            }

            checkNotNull(Godot.nativescript.godot_nativescript_register_method)(
                nativescriptHandle,
                className.cstr.ptr,
                methodName.cstr.ptr,
                attribs,
                instanceMethod
            )
        }
    }

    fun dispose() {
        disposables.forEach { it.asStableRef<Any>().dispose() }
    }
}
