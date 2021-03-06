package godot.codegen

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File


infix fun File.generateApiFrom(jsonSource: File) {
    val classes: List<Class> = ObjectMapper().readValue(jsonSource, object : TypeReference<ArrayList<Class>>() {})

    val tree = classes.buildTree()
    val icalls = mutableSetOf<ICall>()

    classes.forEach { clazz ->
        clazz.generate(this, tree, icalls)
    }

    val iCallFileSpec = FileSpec
        .builder("godot.icalls", "__icalls")
        .addFunction(generateICallsVarargsFunction())
        .addImport("kotlinx.cinterop", "set", "get", "pointed")

    icalls.forEach { iCallFileSpec.addFunction(it generateICall tree) }

    this.parentFile.mkdirs()

    iCallFileSpec
        .addComment("THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD")
        .build()
        .writeTo(this)

    generateEngineTypesRegistration(classes).writeTo(this)
}

private fun generateICallsVarargsFunction(): FunSpec {
    return FunSpec
        .builder("_icall_varargs")
        .addModifiers(KModifier.INTERNAL)
        .returns(ClassName("godot.core", "Variant"))
        .addParameter(
            "mb",
            ClassName("kotlinx.cinterop", "CPointer")
                .parameterizedBy(ClassName("godot.gdnative", "godot_method_bind"))
        )
        .addParameter(
            "inst",
            ClassName("kotlinx.cinterop", "COpaquePointer")
        )
        .addParameter(
            "arguments",
            ClassName("kotlin", "Array").parameterizedBy(STAR)
        )
        .addStatement(
            """return %M {
                            |    val args = %M<%T<%M>>(arguments.size)
                            |    for ((i,arg) in arguments.withIndex()) args[i] = %T.wrap(arg)._handle.ptr
                            |    val result = %T.gdnative.godot_method_bind_call!!.%M(mb, inst, args, arguments.size, null)
                            |    for (i in arguments.indices) %T.gdnative.godot_variant_destroy!!.%M(args[i])
                            |    %T(result)
                            |}
                            |""".trimMargin(),
            MemberName("kotlinx.cinterop", "memScoped"),
            MemberName("kotlinx.cinterop", "allocArray"),
            ClassName("kotlinx.cinterop", "CPointerVar"),
            MemberName("godot.gdnative", "godot_variant"),
            ClassName("godot.core", "Variant"),
            ClassName("godot.core", "Godot"),
            MemberName("kotlinx.cinterop", "invoke"),
            ClassName("godot.core", "Godot"),
            MemberName("kotlinx.cinterop", "invoke"),
            ClassName("godot.core", "Variant")
        )
        .build()
}

private fun generateEngineTypesRegistration(classes: List<Class>): FileSpec {
    val funBuilder = FunSpec.builder("registerEngineTypes")
        .addModifiers(KModifier.INTERNAL)
        .receiver(ClassName("godot.core", "TypeManager"))

    classes.filter { !it.isSingleton && it.newName != "Object" && it.shouldGenerate}.forEach {
        funBuilder.addStatement(
            "registerEngineType(%S, ::%T)",
            it.newName,
            ClassName(it.newName.getPackage(), it.newName)
        )
    }
    return FileSpec.builder("godot", "registerEngineTypes")
        .addFunction(
            funBuilder.build()
        )
        .build()
}
