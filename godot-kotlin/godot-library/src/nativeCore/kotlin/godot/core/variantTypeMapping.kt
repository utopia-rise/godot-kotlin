@file:Suppress("EXPERIMENTAL_API_USAGE")

package godot.core

import godot.Object
import kotlin.reflect.KClass

@PublishedApi
internal val typeConversionFunctions: Map<KClass<out Any>, (Variant) -> Any?> = mapOf(
    Boolean::class to Variant::asBoolean,
    Int::class to Variant::asInt,
    Long::class to Variant::asLong,
    Float::class to Variant::asFloat,
    Double::class to Variant::asDouble,
    String::class to Variant::asString,
    Vector2::class to Variant::asVector2,
    Rect2::class to Variant::asRect2,
    Vector3::class to Variant::asVector3,
    Transform2D::class to Variant::asTransform2D,
    Plane::class to Variant::asPlane,
    Quat::class to Variant::asQuat,
    AABB::class to Variant::asAABB,
    Basis::class to Variant::asBasis,
    Transform::class to Variant::asTransform,
    Color::class to Variant::asColor,
    NodePath::class to Variant::asNodePath,
    RID::class to Variant::asRID,
    Object::class to Variant::asObject,
    Dictionary::class to Variant::asDictionary,
    VariantArray::class to Variant::asVariantArray,
    BoolVariantArray::class to Variant::asBoolVariantArray,
    IntVariantArray::class to Variant::asIntVariantArray,
    RealVariantArray::class to Variant::asRealVariantArray,
    StringVariantArray::class to Variant::asStringVariantArray,
    AABBArray::class to Variant::asAABBArray,
    BasisArray::class to Variant::asBasisArray,
    ColorArray::class to Variant::asColorArray,
    NodePathArray::class to Variant::asNodePathArray,
    PlaneArray::class to Variant::asPlaneArray,
    QuatArray::class to Variant::asQuatArray,
    Rect2Array::class to Variant::asRect2Array,
    RIDArray::class to Variant::asRIDArray,
    Transform2DArray::class to Variant::asTransform2DArray,
    TransformArray::class to Variant::asTransformArray,
    Vector2Array::class to Variant::asVector2Array,
    Vector3Array::class to Variant::asVector3Array,
    PoolByteArray::class to Variant::asPoolByteArray,
    PoolIntArray::class to Variant::asPoolIntArray,
    PoolRealArray::class to Variant::asPoolRealArray,
    PoolStringArray::class to Variant::asPoolStringArray,
    PoolVector2Array::class to Variant::asPoolVector2Array,
    PoolVector3Array::class to Variant::asPoolVector3Array,
    PoolColorArray::class to Variant::asPoolColorArray
)
