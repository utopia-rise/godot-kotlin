package godot.core

abstract class Signal(
    val name: String
) {
    protected fun emitSignal(instance: Object, vararg args: Any?) {
        instance.emitSignal(name, *args)
    }

    @PublishedApi
    internal fun connect(
        instance: Object,
        target: Object,
        method: String,
        binds: List<Any>?,
        flags: Int
    ) {
        val extraArgs = VariantArray<Any>()
        binds?.forEach { extraArgs.add(it) }
        instance.connect(name, target, method, extraArgs, flags)
    }
}

class Signal0(name: String) : Signal(name) {
    internal fun emit(instance: Object) {
        emitSignal(instance)
    }
}

class Signal1<P0>(name: String) : Signal(name) {
    internal fun emit(instance: Object, p0: P0) {
        emitSignal(
            instance,
            p0
        )
    }
}

class Signal2<P0, P1>(name: String) : Signal(name) {
    internal fun emit(instance: Object, p0: P0, p1: P1) {
        emitSignal(
            instance,
            p0,
            p1
        )
    }
}

class Signal3<P0, P1, P2>(name: String) : Signal(name) {
    internal fun emit(instance: Object, p0: P0, p1: P1, p2: P2) {
        emitSignal(
            instance,
            p0,
            p1,
            p2
        )
    }
}

class Signal4<P0, P1, P2, P3>(name: String) : Signal(name) {
    internal fun emit(instance: Object, p0: P0, p1: P1, p2: P2, p3: P3) {
        emitSignal(
            instance,
            p0,
            p1,
            p2,
            p3
        )
    }
}

class Signal5<P0, P1, P2, P3, P4>(name: String) : Signal(name) {
    internal fun emit(instance: Object, p0: P0, p1: P1, p2: P2, p3: P3, p4: P4) {
        emitSignal(
            instance,
            p0,
            p1,
            p2,
            p3,
            p4
        )
    }
}

class Signal6<P0, P1, P2, P3, P4, P5>(name: String) : Signal(name) {
    internal fun emit(instance: Object, p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) {
        emitSignal(
            instance,
            p0,
            p1,
            p2,
            p3,
            p4,
            p5
        )
    }
}

class Signal7<P0, P1, P2, P3, P4, P5, P6>(name: String) : Signal(name) {
    internal fun emit(instance: Object, p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) {
        emitSignal(
            instance,
            p0,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6
        )
    }
}

class Signal8<P0, P1, P2, P3, P4, P5, P6, P7>(name: String) : Signal(name) {
    internal fun emit(instance: Object, p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) {
        emitSignal(
            instance,
            p0,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7
        )
    }
}

class Signal9<P0, P1, P2, P3, P4, P5, P6, P7, P8>(name: String) : Signal(name) {
    internal fun emit(instance: Object, p0: P0, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) {
        emitSignal(
            instance,
            p0,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8
        )
    }
}

class Signal10<P0, P1, P2, P3, P4, P5, P6, P7, P8, P9>(name: String) : Signal(name) {
    internal fun emit(
        instance: Object,
        p0: P0,
        p1: P1,
        p2: P2,
        p3: P3,
        p4: P4,
        p5: P5,
        p6: P6,
        p7: P7,
        p8: P8,
        p9: P9
    ) {
        emitSignal(
            instance,
            p0,
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            p9
        )
    }
}
