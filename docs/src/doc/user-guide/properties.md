Any property of a registered class can be registered as long as it meets all of the following requirements:  
 
 - Defined inside a registered class  
 - Mutable  
 - Annotated with `@RegisterProperty`  
 - Type can be converted to `Variant`  
 


```kotlin
@RegisterClass
class RotatingCube: Spatial() {
    
    @RegisterProperty  
    lateinit var lateInitProperty: NodePath

    @RegisterProperty
    var propertyWithDefaultValue: Float = 2f
}
```

## Default Values
If you define a default value for a property and `visibleInEditor` (more on that later) is set to `true`, the default value will be set in the `inspector`.   
**Note:** If you set a default value in code and a different value in the `inspector` the value of the `inspector` will override the value in code after `init` and before `_ready`!  
A default value can **only** contain compile time constants and only References to compile time constants! Better you only use refs where you have no other choice like for Enums.  
We try to catch all wrong references during compilation and throw a corresponding exception but we may have missed some cases which then only occur during runtime.


## Registration Configuration
You can customize to some extent how your property should be registered in Godot:

The `@RegisterProperty` annotation takes two arguments:

- **visibleInEditor**: If set to `true` the property is visible in the `inspector`. Default: `true`
- **rpcMode**: Default: `RPCMode.DISABLED`

## Type Hint Registration
This binding provides a plethora of annotations for defining Property Type Hints. These annotations are for the `inspector` to provide proper hints and editors to set and change values from within the inspector (like a color wheel, checkboxes, file dialogs, and so on...).  
Each property hint annotation can only be added to certain types of properties. Currently, the checks if the type is correct happens during compilation as we do not have an IDEA plugin yet. If the type is not correct, the compilation will fail.  
Below is a list of currently implemented type hints:  

| Annotation      | Type of Property           | Arguments                                                             | Short Description                                                                                                                                |
|-----------------|----------------------------|-----------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| IntRange        | Int                        | start: Int, end: Int, step: Int = -1, or: Range = Range.NONE          | Provides a range of ints from start to end, with optional steps, and optional `lesser or greater`                                                |
| FloatRange      | Float                      | start: Float, end: Float, step: Float = -1, or: Range = Range.NONE    | Provides a range of floats from start to end, with optional steps, and optional `lesser or greater`                                              |
| DoubleRange     | Double                     | start: Double, end: Double, step: Double = -1, or: Range = Range.NONE | Provides a range of doubles from start to end, with optional steps, and optional `lesser or greater`                                             |
| ExpRange        | Float Double               | start: Float, end: Float, step: Float = -1, or: Range = Range.NONE    | Provides a exponential range of doubles or floats from start to end, with optional steps, and optional `lesser or greater`                       |
| EnumTypeHint    | Enum                       |                                                                       | Registers an enum. The editor then provides a selection of the possible enum values                                                              |
| ExpEasing       | Float Double               | attenuation: Boolean = false, inOut: Boolean = true                   | N/A                                                                                                                                              |
| EnumFlag        | Set<Enum> MutableSet<Enum> |                                                                       | Registers a flag with the enum names set as the flag names. The values in the set define which flags are set.                                    |
| IntFlag         | Int                        | names: vararg String                                                  | Same as enum flag but the `names` set which values can be set in the inspector and no automatic conversion to the individual flag values happen. |
| File            | String                     | extensions: Array<String> = [], global: Boolean = false               | The inspector will show a File dialog in which you can select a File. The Path of the file will be stored in the property.                       |
| Dir             | String                     | global: Boolean = false                                               | The inspector will show a File dialog in which you can select a directory. The Path of the directory will be stored in the property.             |
| MultilineText   | String                     |                                                                       | The inspector shows a multiline text input.                                                                                                      |
| PlaceHolderText | String                     |                                                                       | N/A                                                                                                                                              |
| ColorNoAlpha    | Color                      |                                                                       | The inspector shows a color selection dialog without Alpha                                                                                       |
