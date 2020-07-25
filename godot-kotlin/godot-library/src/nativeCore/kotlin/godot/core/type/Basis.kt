@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package godot.core

import godot.gdnative.godot_basis
import godot.gdnative.godot_basis_layout
import godot.internal.type.*
import kotlinx.cinterop.*
import kotlin.math.*


class Basis(
    p_x: Vector3,
    p_y: Vector3,
    p_z: Vector3
) : CoreType {

    @PublishedApi
    internal var _x = Vector3(p_x)

    @PublishedApi
    internal var _y = Vector3(p_y)

    @PublishedApi
    internal var _z = Vector3(p_z)


    //PROPERTIES
    /** Return a copy of the x Vector3
     * Warning: Writing x.x = 2 will only modify a copy, not the actual object.
     * To modify it, use x().
     * */
    var x
        get() = Vector3(_x)
        set(value) {
            _x = Vector3(value)
        }

    inline fun <T> x(block: Vector3.() -> T): T {
        return _x.block()
    }

    /** Return a copy of the y Vector3
     * Warning: Writing y.x = 2 will only modify a copy, not the actual object.
     * To modify it, use y().
     * */
    var y
        get() = Vector3(_y)
        set(value) {
            _y = Vector3(value)
        }


    inline fun <T> y(block: Vector3.() -> T): T {
        return _y.block()
    }

    /** Return a copy of the z Vector3
     * Warning: Writing z.x = 2 will only modify a copy, not the actual object.
     * To modify it, use z().
     * */
    var z
        get() = Vector3(_z)
        set(value) {
            _z = Vector3(value)
        }

    inline fun <T> z(block: Vector3.() -> T): T {
        return _z.block()
    }

    //CONSTANTS
    companion object {
        val IDENTITY: Basis
            get() = Basis(1, 0, 0, 0, 1, 0, 0, 0, 1)
        val FLIP_X: Basis
            get() = Basis(-1, 0, 0, 0, 1, 0, 0, 0, 1)
        val FLIP_Y: Basis
            get() = Basis(1, 0, 0, 0, -1, 0, 0, 0, 1)
        val FLIP_Z: Basis
            get() = Basis(1, 0, 0, 0, 1, 0, 0, 0, -1)

        //used internally by a few methods
        private val orthoBases: Array<Basis> =
            arrayOf(
                Basis(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0),
                Basis(0.0, -1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0),
                Basis(-1.0, 0.0, 0.0, 0.0, -1.0, 0.0, 0.0, 0.0, 1.0),
                Basis(0.0, 1.0, 0.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0),
                Basis(1.0, 0.0, 0.0, 0.0, 0.0, -1.0, 0.0, 1.0, 0.0),
                Basis(0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0),
                Basis(-1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0),
                Basis(0.0, 0.0, -1.0, -1.0, 0.0, 0.0, 0.0, 1.0, 0.0),
                Basis(1.0, 0.0, 0.0, 0.0, -1.0, 0.0, 0.0, 0.0, -1.0),
                Basis(0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, -1.0),
                Basis(-1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, -1.0),
                Basis(0.0, -1.0, 0.0, -1.0, 0.0, 0.0, 0.0, 0.0, -1.0),
                Basis(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0),
                Basis(0.0, 0.0, -1.0, 1.0, 0.0, 0.0, 0.0, -1.0, 0.0),
                Basis(-1.0, 0.0, 0.0, 0.0, 0.0, -1.0, 0.0, -1.0, 0.0),
                Basis(0.0, 0.0, 1.0, -1.0, 0.0, 0.0, 0.0, -1.0, 0.0),
                Basis(0.0, 0.0, 1.0, 0.0, 1.0, 0.0, -1.0, 0.0, 0.0),
                Basis(0.0, -1.0, 0.0, 0.0, 0.0, 1.0, -1.0, 0.0, 0.0),
                Basis(0.0, 0.0, -1.0, 0.0, -1.0, 0.0, -1.0, 0.0, 0.0),
                Basis(0.0, 1.0, 0.0, 0.0, 0.0, -1.0, -1.0, 0.0, 0.0),
                Basis(0.0, 0.0, 1.0, 0.0, -1.0, 0.0, 1.0, 0.0, 0.0),
                Basis(0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0),
                Basis(0.0, 0.0, -1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0),
                Basis(0.0, -1.0, 0.0, 0.0, 0.0, -1.0, 1.0, 0.0, 0.0)
            )
    }


    //CONSTRUCTOR
    constructor() :
        this(Vector3(1, 0, 0), Vector3(1, 1, 0), Vector3(1, 0, 1))

    constructor(other: Basis) :
        this(other._x, other._y, other._z)

    constructor(
        xx: Number,
        xy: Number,
        xz: Number,
        yx: Number,
        yy: Number,
        yz: Number,
        zx: Number,
        zy: Number,
        zz: Number
    ) : this(Vector3(xx, xy, xz), Vector3(yx, yy, yz), Vector3(zx, zy, zz))


    constructor(from: Vector3) : this() {
        setEuler(from)
    }

    constructor(quat: Quat) : this() {
        val d = quat.lengthSquared()
        val s = 2.0 / d
        val xs = quat.x * s
        val ys = quat.y * s
        val zs = quat.z * s
        val wx = quat.w * xs
        val wy = quat.w * ys
        val wz = quat.w * zs
        val xx = quat.x * xs
        val xy = quat.x * ys
        val xz = quat.x * zs
        val yy = quat.y * ys
        val yz = quat.y * zs
        val zz = quat.z * zs
        set(
            1.0 - (yy + zz), xy - wz, xz + wy,
            xy + wz, 1.0 - (xx + zz), yz - wx,
            xz - wy, yz + wx, 1.0 - (xx + yy)
        )
    }

    constructor(axis: Vector3, phi: RealT) : this() {
        // Rotation matrix from axis and angle, see https://en.wikipedia.org/wiki/Rotation_matrix#Rotation_matrix_from_axis_and_angle
        val axisq = Vector3(axis.x * axis.x, axis.y * axis.y, axis.z * axis.z)

        val cosine: RealT = cos(phi)
        val sine: RealT = sin(phi)

        apply {
            _x.x = axisq.x + cosine * (1.0 - axisq.x)
            _x.y = axis.x * axis.y * (1.0 - cosine) - axis.z * sine
            _x.z = axis.z * axis.x * (1.0 - cosine) + axis.y * sine

            _y.x = axis.x * axis.y * (1.0 - cosine) + axis.z * sine
            _y.y = axisq.y + cosine * (1.0 - axisq.y)
            _y.z = axis.y * axis.z * (1.0 - cosine) - axis.x * sine

            _z.x = axis.z * axis.x * (1.0 - cosine) - axis.y * sine
            _z.y = axis.y * axis.z * (1.0 - cosine) + axis.x * sine
            _z.z = axisq.z + cosine * (1.0 - axisq.z)
        }
    }

    internal constructor(native: CValue<godot_basis>) : this() {
        memScoped {
            this@Basis.setRawMemory(native.ptr)
        }
    }

    internal constructor(mem: COpaquePointer) : this() {
        this.setRawMemory(mem)
    }


    //INTEROP
    override fun getRawMemory(memScope: MemScope): COpaquePointer {
        val value = cValue<godot_basis_layout> {
            x.x = this@Basis._x.x.toGodotReal()
            x.y = this@Basis._x.y.toGodotReal()
            x.z = this@Basis._x.z.toGodotReal()
            y.x = this@Basis._y.x.toGodotReal()
            y.y = this@Basis._y.y.toGodotReal()
            y.z = this@Basis._y.z.toGodotReal()
            z.x = this@Basis._z.x.toGodotReal()
            z.y = this@Basis._z.y.toGodotReal()
            z.z = this@Basis._z.z.toGodotReal()
        }
        return value.getPointer(memScope)
    }

    override fun setRawMemory(mem: COpaquePointer) {
        val value = mem.reinterpret<godot_basis_layout>().pointed
        _x.setRawMemory(value.x.ptr)
        _y.setRawMemory(value.y.ptr)
        _z.setRawMemory(value.z.ptr)
    }


    //API
    /**
     * Returns the determinant of the matrix.
     */
    fun determinant(): RealT {
        return this._x.x * (this._y.y * this._z.z - this._z.y * this._y.z) -
            this._y.x * (this._x.y * this._z.z - this._z.y * this._x.z) +
            this._z.x * (this._x.y * this._y.z - this._y.y * this._x.z)
    }

    /**
     *
     */
    fun getEuler(): Vector3 {
        return getEulerYxz()
    }

    /**
     * getEulerXyz returns a vector containing the Euler angles in the format
     * (a1,a2,a3), where a3 is the angle of the first rotation, and a1 is the last
     * (following the convention they are commonly defined in the literature).
     *
     * The current implementation uses XYZ convention (Z is the first rotation),
     * so euler.z is the angle of the (first) rotation around Z axis and so on,
     *
     * And thus, assuming the matrix is a rotation matrix, this function returns
     * @return the angles in the decomposition R = X(a1).Y(a2).Z(a3) where Z(a) rotates
     */
    internal fun getEulerXyz(): Vector3 {
        // Euler angles in XYZ convention.
        // See https://en.wikipedia.org/wiki/Euler_angles#Rotation_matrix
        //
        // rot =  cy*cz          -cy*sz           sy
        //        cz*sx*sy+cx*sz  cx*cz-sx*sy*sz -cy*sx
        //       -cx*cz*sy+sx*sz  cz*sx+cx*sy*sz  cx*cy
        val euler = Vector3()
        if (!isRotation()) return euler

        val sy = this._x.z
        if (sy < 1.0) {
            if (sy > -1.0) {
                // is this a pure Y rotation?
                if (isEqualApprox(this._y.x, 0.0)
                    && isEqualApprox(this._x.y, 0.0)
                    && isEqualApprox(this._y.z, 0.0)
                    && isEqualApprox(this._z.y, 0.0)
                    && isEqualApprox(this._y.y, 1.0)
                ) {
                    // return the simplest form (human friendlier in editor and scripts)
                    euler.x = 0.0
                    euler.y = atan2(this._x.z, this._x.x)
                    euler.z = 0.0
                } else {
                    euler.x = atan2(-this._y.z, this._z.z)
                    euler.y = asin(sy)
                    euler.z = atan2(-this._x.y, this._x.x)
                }
            } else {
                euler.x = -atan2(this._x.y, this._y.y)
                euler.y = (-PI).toRealT() / 2.0
                euler.z = 0.0
            }
        } else {
            euler.x = atan2(this._x.y, this._y.y)
            euler.y = PI.toRealT() / 2.0
            euler.z = 0.0
        }
        return euler
    }

    /**
     * getEulerYxz returns a vector containing the Euler angles in the YXZ convention,
     * as in first-Z, then-X, last-Y. The angles for X, Y, and Z rotations are returned
     * as the x, y, and z components of a Vector3 respectively.
     */
    internal fun getEulerYxz(): Vector3 {

        // Euler angles in YXZ convention.
        // See https://en.wikipedia.org/wiki/Euler_angles#Rotation_matrix
        //
        // rot =  cy*cz+sy*sx*sz    cz*sy*sx-cy*sz        cx*sy
        //        cx*sz             cx*cz                 -sx
        //        cy*sx*sz-cz*sy    cy*cz*sx+sy*sz        cy*cx

        val euler = Vector3()

        if (!isRotation()) return euler
        val m12 = this._y.z
        if (m12 < 1.0) {
            if (m12 > -1.0) {
                // is this a pure X rotation?
                if (isEqualApprox(this._y.x, 0.0) && isEqualApprox(this._x.y, 0.0) && isEqualApprox(
                        this._x.z,
                        0.0
                    ) && isEqualApprox(this._z.x, 0.0) && isEqualApprox(this._x.x, 1.0)
                ) {
                    // return the simplest form (human friendlier in editor and scripts)
                    euler.x = atan2(-m12, this._y.y)
                    euler.y = 0.0
                    euler.z = 0.0
                } else {
                    euler.x = asin(-m12)
                    euler.y = atan2(this._x.z, this._z.z)
                    euler.z = atan2(this._y.x, this._y.y)
                }
            } else { // m12 == -1
                euler.x = PI.toRealT() * 0.5
                euler.y = -atan2(-this._x.y, this._x.x)
                euler.z = 0.0
            }
        } else { // m12 == 1
            euler.x = (-PI).toRealT() * 0.5
            euler.y = -atan2(-this._x.y, this._x.x)
            euler.z = 0.0
        }

        return euler
    }


    private fun isOrthogonal(): Boolean {
        val id = Basis()
        val m = this.transposed()
        return m.isEqualApprox(id)
    }

    private fun isRotation(): Boolean =
        abs(determinant() - 1) < CMP_EPSILON && isOrthogonal()

    /**
     * This function considers a discretization of rotations into 24 points on unit sphere,
     * lying along the vectors (x,y,z) with each component being either -1,0 or 1,
     * and returns the index of the point best representing the orientation of the object.
     * It is mainly used by the grid map editor. For further details, refer to Godot source code.
     */
    fun getOrthogonalIndex(): Int {
        val orth = this
        for (i in 0..2) {
            for (j in 0..2) {
                var v = orth[i][j]
                v = when {
                    v > 0.5 -> 1.0
                    v < -0.5 -> -1.0
                    else -> 0.0
                }
                orth[i][j] = v
            }
        }

        for (i in 0..23) {
            if (orthoBases[i] == orth) {
                return i
            }
        }
        return 0
    }

    /**
     *
     */
    fun getRotationQuat(): Quat {
        // Assumes that the matrix can be decomposed into a proper rotation and scaling matrix as M = R.S,
        // and returns the Euler angles corresponding to the rotation part, complementing get_scale().
        // See the comment in get_scale() for further information.
        val m = orthonormalized()
        val det: RealT = m.determinant().toRealT()
        if (det < 0) {
            // Ensure that the determinant is 1, such that result is a proper rotation matrix which can be represented by Euler angles.
            m.scale(Vector3(-1, -1, -1))
        }

        return Quat(m)
    }

    /**
     * Assuming that the matrix is the combination of a rotation and scaling,
     * return the absolute value of scaling factors along each axis.
     */
    fun getScale(): Vector3 {
        // We are assuming M = R.S, and performing a polar decomposition to extract R and S.
        // FIXME: We eventually need a proper polar decomposition.
        // As a cheap workaround until then, to ensure that R is a proper rotation matrix with determinant +1
        // (such that it can be represented by a Quat or Euler angles), we absorb the sign flip into the scaling matrix.
        // As such, it works in conjuction with getRotation().
        val detSign: RealT = if (determinant() > 0) 1.0 else -1.0
        return detSign * Vector3(
            Vector3(this._x.x, this._y.x, this._z.x).length(),
            Vector3(this._x.y, this._y.y, this._z.y).length(),
            Vector3(this._x.z, this._y.z, this._z.z).length()
        )
    }

    /**
     * Returns the inverse of the matrix.
     */
    fun inverse(): Basis {
        val b = Basis(this)
        b.invert()
        return b
    }

    internal fun invert() {
        inline fun cofac(row1: Int, col1: Int, row2: Int, col2: Int): RealT {
            return this[row1][col1] * this[row2][col2] - this[row1][col2] * this[row2][col1]
        }

        val co1 = _y.y * _z.z - _y.z * _z.y
        val co2 = _y.z * _z.x - _y.x - _z.z
        val co3 = _y.x * _z.y - _y.y * _z.x

        val det: RealT = this._x.x * co1 + this._x.y * co2 + this._x.z * co3


        if (isEqualApprox(det, 0.0)) {
            Godot.printError("determinant = 0", "invert", "Basis.kt", 372)
            return
        }

        val s = 1.0 / det
        set(
            co1 * s, (_x.z * _z.y - _x.y * _z.z) * s, (_x.y * _y.z - _x.z * _y.y) * s,
            co2 * s, (_x.x * _z.z - _x.z * _z.x) * s, (_x.z * _y.x - _x.x * _y.z) * s,
            co3 * s, (_x.y * _z.x - _x.x * _z.y) * s, (_x.x * _y.y - _x.y * _y.x) * s
        )
    }

    /**
     *
     */
    fun isEqualApprox(a: Basis, epsilon: RealT = CMP_EPSILON): Boolean {
        if (isEqualApprox(this.x.x, a.x.x, epsilon)) return false
        if (isEqualApprox(this.x.y, a.x.y, epsilon)) return false
        if (isEqualApprox(this.x.z, a.x.z, epsilon)) return false

        if (isEqualApprox(this.y.x, a.y.x, epsilon)) return false
        if (isEqualApprox(this.y.y, a.y.y, epsilon)) return false
        if (isEqualApprox(this.y.x, a.y.x, epsilon)) return false

        if (isEqualApprox(this.z.x, a.z.x, epsilon)) return false
        if (isEqualApprox(this.z.y, a.z.y, epsilon)) return false
        if (isEqualApprox(this.z.z, a.z.z, epsilon)) return false

        return true
    }

    /**
     * Returns the orthonormalized version of the matrix (useful to call from time to time to avoid rounding error for orthogonal matrices).
     * This performs a Gram-Schmidt orthonormalization on the basis of the matrix.
     */
    fun orthonormalized(): Basis {
        val b = Basis(this)
        b.orthonormalize()
        return b
    }

    internal fun orthonormalize() {
        if (isEqualApprox(determinant(), 0.0)) {
            Godot.printError("determinant == 0\n", "orthonormalize()", "Basis.kt", 375)
            return
        }

        val x = getAxis(0)
        var y = getAxis(1)
        var z = getAxis(2)

        x.normalize()
        y = (y - x * (x.dot(y)))
        y.normalize()
        z = (z - x * (x.dot(z)) - y * (y.dot(z)))
        z.normalize()

        setAxis(0, x)
        setAxis(1, y)
        setAxis(2, z)
    }

    private fun getAxis(axis: Int): Vector3 =
        Vector3(this._x[axis], this._y[axis], this._z[axis])

    private fun setAxis(axis: Int, value: Vector3) {
        this._x[axis] = value.x
        this._y[axis] = value.y
        this._z[axis] = value.z
    }

    /**
     * Introduce an additional rotation around the given axis by phi (radians). The axis must be a normalized vector.
     */
    fun rotated(axis: Vector3, phi: RealT): Basis {
        return Basis(axis, phi) * this
    }

    internal fun rotate(axis: Vector3, phi: RealT) {
        val ret = rotated(axis, phi)
        this._x = ret._x
        this._y = ret._y
        this._z = ret._z
    }

    /**
     * Introduce an additional scaling specified by the given 3D scaling factor.
     */
    fun scaled(scale: Vector3): Basis {
        val b = Basis(this)
        b.scale(scale)
        return b
    }

    internal fun scale(scale: Vector3) {
        this._x.x *= scale.x
        this._x.y *= scale.x
        this._x.z *= scale.x
        this._y.x *= scale.y
        this._y.y *= scale.y
        this._y.z *= scale.y
        this._z.x *= scale.z
        this._z.y *= scale.z
        this._z.z *= scale.z
    }

    /**
     *
     */
    fun setEuler(p_euler: Vector3) {
        setEulerYxz(p_euler)
    }

    /**
     * setEulerXyz expects a vector containing the Euler angles in the format
     * (ax,ay,az), where ax is the angle of rotation around x axis,
     * and similar for other axes.
     * The current implementation uses XYZ convention (Z is the first rotation).
     */
    internal fun setEulerXyz(euler: Vector3) {

        var c: RealT = cos(euler.x)
        var s: RealT = sin(euler.x)

        val xmat = Basis(1.0, 0.0, 0.0, 0.0, c, -s, 0.0, s, c)

        c = cos(euler.y)
        s = sin(euler.y)
        val ymat = Basis(c, 0.0, s, 0.0, 1.0, 0.0, -s, 0.0, c)

        c = cos(euler.z)
        s = sin(euler.z)
        val zmat = Basis(c, -s, 0.0, s, c, 0.0, 0.0, 0.0, 1.0)

        //optimizer will optimize away all this anyway
        val ret = xmat * (ymat * zmat)
        this._x = ret._x
        this._y = ret._y
        this._z = ret._z
    }

    /**
     * setEulerYxz expects a vector containing the Euler angles in the format
     * (ax,ay,az), where ax is the angle of rotation around x axis,
     * and similar for other axes.
     * The current implementation uses YXZ convention (Z is the first rotation).
     */
    internal fun setEulerYxz(euler: Vector3) {
        var c: RealT = cos(euler.x)
        var s: RealT = sin(euler.x)

        val xmat = Basis(1.0, 0.0, 0.0, 0.0, c, -s, 0.0, s, c)

        c = cos(euler.y)
        s = sin(euler.y)
        val ymat = Basis(c, 0.0, s, 0.0, 1.0, 0.0, -s, 0.0, c)

        c = cos(euler.z)
        s = sin(euler.z)
        val zmat = Basis(c, -s, 0.0, s, c, 0.0, 0.0, 0.0, 1.0)

        val ret = ymat * xmat * zmat

        this._x = ret._x
        this._y = ret._y
        this._z = ret._z
    }

    /**
     *
     */
    fun setOrthogonalIndex(index: Int) {
        if (index >= 24) {
            Godot.printError("index >= 24", "setOrthogonalIndex($index)", "Basis.kt", 493)
            return
        }
        val ret = orthoBases[index]
        this._x = ret._x
        this._y = ret._y
        this._z = ret._z
    }

    /**
     * Assuming that the matrix is a proper rotation matrix, slerp performs a spherical-linear interpolation with another rotation matrix.
     */
    fun slerp(b: Basis, t: RealT): Basis {
        if (!this.isRotation()) {
            Godot.printError("Basis is not a rotation", "slerp()", "Basis.kt", 504)
        }

        val from = Quat(this)
        val to = Quat(b)

        val ret = Basis(from.slerp(to, t))
        ret._x *= (b._x.length() - this._x.length()) * t
        ret._y *= (b._y.length() - this._y.length()) * t
        ret._z *= (b._z.length() - this._z.length()) * t

        return ret;
    }

    /**
     * Transposed dot product with the x axis of the matrix.
     */
    fun tdotx(v: Vector3): RealT {
        return this._x.x * v.x + this._y.x * v.y + this._z.x * v.z
    }

    /**
     * Transposed dot product with the y axis of the matrix.
     */
    fun tdoty(v: Vector3): RealT {
        return this._x.y * v.x + this._y.y * v.y + this._z.y * v.z
    }

    /**
     * Transposed dot product with the z axis of the matrix.
     */
    fun tdotz(v: Vector3): RealT {
        return this._x.z * v.x + this._y.z * v.y + this._z.z * v.z
    }

    /**
     * Returns the transposed version of the matrix.
     */
    fun transposed(): Basis {
        val b = Basis(this)
        b.transpose()
        return b
    }

    internal fun transpose() {
        this._x.y = this._y.x.also { this._y.x = this._x.y }
        this._x.z = this._z.x.also { this._z.x = this._x.z }
        this._y.z = this._z.y.also { this._z.y = this._y.z }
    }

    /**
     * Returns a vector transformed (multiplied) by the matrix.
     */
    fun xform(vector: Vector3): Vector3 =
        Vector3(
            this._x.dot(vector),
            this._y.dot(vector),
            this._z.dot(vector)
        )

    /**
     * Returns a vector transformed (multiplied) by the transposed matrix.
     * Note that this results in a multiplication by the inverse of the matrix only if it represents a rotation-reflection.
     */
    fun xformInv(vector: Vector3): Vector3 =
        Vector3(
            (this._x.x * vector.x) + (this._y.x * vector.y) + (this._z.x * vector.z),
            (this._x.y * vector.x) + (this._y.y * vector.y) + (this._z.y * vector.z),
            (this._x.z * vector.x) + (this._y.z * vector.y) + (this._z.z * vector.z)
        )


    //UTILITIES
    override fun toVariant() = Variant(this)

    operator fun get(n: Int): Vector3 {
        return when (n) {
            0 -> _x
            1 -> _y
            2 -> _z
            else -> throw IndexOutOfBoundsException()
        }
    }

    operator fun set(n: Int, f: Vector3) {
        when (n) {
            0 -> _x = f
            1 -> _y = f
            2 -> _z = f
            else -> throw IndexOutOfBoundsException()
        }
    }

    fun set(
        xx: RealT,
        xy: RealT,
        xz: RealT,
        yx: RealT,
        yy: RealT,
        yz: RealT,
        zx: RealT,
        zy: RealT,
        zz: RealT
    ) {
        _x.x = xx; _x.y = xy; _x.z = xz
        _y.x = yx; _y.y = yy; _y.z = yz
        _z.x = zx; _z.y = zy; _z.z = zz
    }


    operator fun plus(matrix: Basis) = Basis(this._x + matrix._x, this._y + matrix._y, this._z + matrix._z)

    operator fun minus(matrix: Basis) = Basis(this._x - matrix._x, this._y - matrix._y, this._z - matrix._z)

    operator fun times(matrix: Basis) = Basis(
        matrix.tdotx(this._x), matrix.tdoty(this._x), matrix.tdotz(this._x),
        matrix.tdotx(this._y), matrix.tdoty(this._y), matrix.tdotz(this._y),
        matrix.tdotx(this._z), matrix.tdoty(this._z), matrix.tdotz(this._z)
    )

    operator fun times(scalar: Int) = Basis(this._x * scalar, this._y * scalar, this._z * scalar)
    operator fun times(scalar: Long) = Basis(this._x * scalar, this._y * scalar, this._z * scalar)
    operator fun times(scalar: Float) = Basis(this._x * scalar, this._y * scalar, this._z * scalar)
    operator fun times(scalar: Double) = Basis(this._x * scalar, this._y * scalar, this._z * scalar)

    override fun toString(): String {
        return buildString {
            append("${this@Basis._x.x}, ${this@Basis._x.y}, ${this@Basis._x.z}, ")
            append("${this@Basis._y.x}, ${this@Basis._y.y}, ${this@Basis._y.z}, ")
            append("${this@Basis._z.x}, ${this@Basis._z.y}, ${this@Basis._z.z}")
        }
    }

    override fun equals(other: Any?): Boolean =
        when (other) {
            is Basis -> (this._x.x == other._x.x && this._x.y == other._x.y && this._x.z == other._x.z &&
                this._y.x == other._y.x && this._y.y == other._y.y && this._y.z == other._y.z &&
                this._z.x == other._z.x && this._z.y == other._z.y && this._z.z == other._z.z)
            else -> throw IllegalArgumentException()
        }

    override fun hashCode(): Int {
        var result = _x.hashCode()
        result = 31 * result + _y.hashCode()
        result = 31 * result + _z.hashCode()
        return result
    }
}

operator fun Int.times(basis: Basis) = basis * this
operator fun Long.times(basis: Basis) = basis * this
operator fun Float.times(basis: Basis) = basis * this
operator fun Double.times(basis: Basis) = basis * this
