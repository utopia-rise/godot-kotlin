import java.util.*

val bintrayUser: String by project
val bintrayKey: String by project
val platform: String by project
val armArch: String by project

plugins {
    id("kotlin-multiplatform")
    id("maven-publish")
    id("com.jfrog.bintray")
}

group = "org.godotengine.kotlin"
version = Dependencies.godotLibraryExtensionVersion

kotlin {
    sourceSets {
        sourceSets.create("macosMain")
        sourceSets.create("linuxMain")
        sourceSets.create("windowsMain")
        sourceSets.create("iosArm64Main")
        sourceSets.create("iosX64Main")
        configure(listOf(
                sourceSets["macosMain"],
                sourceSets["linuxMain"],
                sourceSets["windowsMain"],
                sourceSets["iosArm64Main"],
                sourceSets["iosX64Main"]
        )) {
            this.kotlin.srcDir("src/main/kotlin")
        }
    }

    val targets =
            if (project.hasProperty("platform")) {
                when (platform) {
                    "windows" -> listOf(targetFromPreset(presets["mingwX64"], "windows"))
                    "linux" -> listOf(targetFromPreset(presets["linuxX64"], "linux"))
                    "macos" -> listOf(targetFromPreset(presets["macosX64"], "macos"))
                    "ios" -> if (project.hasProperty("armArch")) {
                        when (armArch) {
                            "arm64" -> listOf(targetFromPreset(presets["iosArm64"], "iosArm64"))
                            "X64" -> listOf(targetFromPreset(presets["iosX64"], "iosX64"))
                            else -> listOf(targetFromPreset(presets["iosArm64"], "iosArm64"))
                        }
                    } else listOf(targetFromPreset(presets["iosArm64"], "iosArm64"))
                    else -> listOf(targetFromPreset(presets["linuxX64"], "linux"))
                }
            } else {
                listOf(
                        targetFromPreset(presets["linuxX64"], "linux"),
                        targetFromPreset(presets["macosX64"], "macos"),
                        targetFromPreset(presets["mingwX64"], "windows"),
                        targetFromPreset(presets["iosArm64"], "iosArm64"),
                        targetFromPreset(presets["iosX64"], "iosX64")
                )
            }

    targets.forEach {
        it.compilations.all {
            dependencies {
                implementation(project(":wrapper:godot-library"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3")
            }
        }
    }
}

tasks.build {
    finalizedBy(tasks.publishToMavenLocal)
}

if (project.hasProperty("bintrayUser") && project.hasProperty("bintrayKey")
        && project.hasProperty("platform")) {
    bintray {
        user = bintrayUser
        key = bintrayKey
        setPublications(platform)
        pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
            userOrg = "utopia-rise"
            repo = "kotlin-godot"

            name = "${project.name}-$platform"
            vcsUrl = "https://github.com/utopia-rise/kotlin-godot-wrapper"
            setLicenses("Apache-2.0")
            version(closureOf<com.jfrog.bintray.gradle.BintrayExtension.VersionConfig> {
                this.name = project.version.toString()
                released = Date().toString()
                description = "Godot library extension ${project.version}"
                vcsTag = project.version.toString()
            })
        })
    }
}