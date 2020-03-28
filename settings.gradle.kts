rootProject.name = "godot-kotlin"

subdir("godot-kotlin") {
  include("godot-library")
}

subdir("plugins") {
  include("godot-gradle-plugin")
}

subdir("entry-generator") {
  include("godot-entry-generator")
}

class IncludeDsl(val root: String) {
  fun include(project: String) {
    settings.include(project)
    settings.project(":$project").also {
      it.projectDir = file("$root/$project")
      it.buildFileName = "$root/$project/build.gradle.kts"
    }
  }
}

fun subdir(root: String, block: IncludeDsl.() -> Unit) {
  block(IncludeDsl(root))
}