import java.util.*

plugins {
    id("java-library")
    id("application")
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
    id("com.jfrog.bintray")
    application
}

version = Dependencies.entryGeneratorVersion
group = "org.godotengine.kotlin"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    compileOnly(project(":tools:annotations"))
    compileOnly(project(":tools:annotations-internal"))
    compileOnly("org.jetbrains.kotlin:kotlin-compiler-embeddable") //used for class analisation without reflection
    implementation("de.jensklingenberg:mpapt-runtime:${Dependencies.mpaptVersion}")
    implementation("com.squareup:kotlinpoet:${Dependencies.kotlinPoetVersion}")
}

application {
    mainClassName = "MainKt"
}

publishing {
    publications {
        register("entryGenerator", MavenPublication::class.java) {
            from(components["java"])
        }
    }
}

tasks.build {
    finalizedBy(tasks.publishToMavenLocal)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val bintrayUser: String by project
val bintrayKey: String by project

if(project.hasProperty("bintrayUser") && project.hasProperty("bintrayKey")) {
    bintray {
        user = bintrayUser
        key = bintrayKey

        setPublications("entryGenerator")
        pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
            userOrg = "utopia-rise"
            repo = "kotlin-godot"

            name = project.name
            vcsUrl = "https://github.com/utopia-rise/kotlin-godot-wrapper"
            setLicenses("Apache-2.0")
            version(closureOf<com.jfrog.bintray.gradle.BintrayExtension.VersionConfig> {
                this.name = project.version.toString()
                released = Date().toString()
                description = "Godot entry generator ${project.version}"
                vcsTag = project.version.toString()
            })
        })
    }
}