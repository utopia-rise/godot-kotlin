import com.github.jengelman.gradle.plugins.shadow.ShadowExtension
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.github.jengelman.gradle.plugins:shadow:${DependenciesVersions.shadowJarPluginVersion}")
    }
}

plugins {
    kotlin("jvm")
    `maven-publish`
}

apply(plugin = "com.github.johnrengelman.shadow")

val embeddable by configurations.creating {
    extendsFrom(configurations.implementation.get())
}

dependencies {
    implementation(project(":godot-annotation-processor"))
    implementation(project(":godot-compiler-plugin-common"))
    implementation("de.jensklingenberg:mpapt-runtime:${DependenciesVersions.mpaptVersion}")
    compileOnly(kotlin("compiler"))
}

val shadowJar by tasks.getting(ShadowJar::class) {
    configurations = listOf(embeddable)
    @Suppress("UnstableApiUsage")
    manifest {
        attributes["Implementation-Title"] = "Godot Kotlin Native Compiler Plugin"
        attributes["Implementation-Version"] = project.version
        attributes["Main-Class"] = "godot.compilerplugin.NativeComponentRegistrar"
    }
    archiveBaseName.set("godot-compiler-native-plugin")
    archiveVersion.set(project.version.toString())
    val classifier: String? = null //needed as we need to specify the type null represents. otherwise we get ambiguous overload exception during build
    archiveClassifier.set(classifier)
}

tasks {
    val sourceJar by creating(Jar::class) {
        archiveBaseName.set(project.name)
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    build {
        finalizedBy(publishToMavenLocal)
    }
}

publishing {
    publications {
        val shadow by creating(MavenPublication::class) {
            pom {
                groupId = "${project.group}"
                artifactId = project.name
                version = "${project.version}"
            }
            project.extensions.getByType(ShadowExtension::class).component(this)
            artifact(tasks.getByName("sourceJar"))
        }
    }
}

project.extra["artifacts"] = arrayOf("shadow")

apply {
    plugin(BintrayPublish::class.java)
}
