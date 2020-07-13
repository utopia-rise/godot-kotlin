package godot.codegen

import com.beust.klaxon.Json


class Argument(
    @Json(name = "name")
    var name: String,
    @Json(name = "type")
    var type: String,
    @Json(name = "has_default_value")
    val hasDefaultValue: Boolean = false,
    @Json(name = "default_value")
    var defaultValue: String = ""
) {
    val nullable: Boolean

    val applyDefault: String?

    init {
        name = name.convertToCamelCase().escapeKotlinReservedNames()
        type = type.convertTypeToKotlin()

        if (defaultValue == "[Object:null]" || defaultValue == "Null") {
            defaultValue = "null"
            nullable = true
        } else {
            nullable = false
        }

        applyDefault = if (hasDefaultValue && nullable) {
            "null"
        } else if (hasDefaultValue) {
            when (type) {
                "Color", "Variant" -> "$type($defaultValue)"
                "Boolean" -> defaultValue.toLowerCase()
                "Double" -> intToFloat(defaultValue)
                "Vector2", "Vector3", "Rect2" -> "$type$defaultValue"
                "Dictionary", "Transform", "Transform2D", "GDArray", "RID", "PoolVector2Array", "PoolStringArray", "PoolVector3Array", "PoolColorArray", "PoolIntArray", "PoolRealArray", "PoolByteArray" -> "$type()"
                "String" -> "\"$defaultValue\""
                else -> defaultValue
            }
        } else {
            null
        }
    }

    private fun intToFloat(defaultValue: String): String {
        if (defaultValue.indexOf('.') != -1) return defaultValue

        return "$defaultValue.0"
    }
}