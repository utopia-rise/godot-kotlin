plugins {
    id("org.ajoberstar.grgit") version "4.0.2"
}

val currentCommit = grgit.head()
// check if the current commit is tagged
var releaseMode = grgit.tag.list().firstOrNull { tag -> tag.commit.id == currentCommit.id } != null
version = "0.1.0-${DependenciesVersions.godotVersion}"
if (!releaseMode) {
    version = "$version-${currentCommit.abbreviatedId}"
}

val versionString = project.version.toString()
println("Inferred version: $versionString (release=$releaseMode)")

subprojects {
    group = "com.utopia-rise"
    version = versionString
    repositories {
        mavenLocal()
        jcenter()
    }
    extra["releaseMode"] = releaseMode
}
