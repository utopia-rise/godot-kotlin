This binding uses [Gradle](https://gradle.org) as its build tool and you will need version 6.0 or higher installed. The next requirement is to have a Godot project (obviously!), if you don't have it yet please create one.

Open a terminal and `cd` to root directory of your Godot project.

## Wrapper
On this step, we will be setting up a Gradle [wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html). The wrapper will ensure that anyone who wants to build your project from source will use the same gradle version.

```shell
touch build.gradle.kts gradle.properties settings.gradle.kts
```

The above command will create three files, which will be empty for now.

```shell
gradle wrapper --gradle-version=6.5.1
```

That is it, you have the wrapper installed! The command will produce several files but the important ones are `gradlew` and `gradlew.bat`. Moving forward we will be using `gradlew` to run gradle (`gradlew.bat` on Windows). The first time `gradlew` is used it will download the gradle version you have specified before.

## Setup
Once you have the wrapper installed, we need to setup the Gradle plugin this binding provides. Without the plugin, you will have to manually generate the entry point, `.gdnlib` and `.gdns` files.

```kotlin
plugins {
    kotlin("multiplatform") version "$kotlinVersion"
    id("com.utopia-rise.godot-kotlin") version "$godotKotlinVersion"
}

repositories {
    jcenter()
    mavenCentral()
}

godot {
    // Build a debug binary
    debug.set(true)
}
```

Let's create a file `src/godotMain/kotlin/Simple.kt` with the following contents.

```kotlin
import godot.*
import godot.core.*

@RegisterClass
class Simple: Spatial() {

    @RegisterFunction
    override fun _ready() {
        GD.print("Hello Godot from Kotlin!")
    }
}
```

More will be explained in the [classes](../user-guide/classes.md) section of user guide, but for now `@RegisterClass` will register
the annotated class to Godot.

!!! note ""
    The plugin automatically generates the appropriate `gdns` files which can be found at `src/gdns`. It is up to you whether you want to include those files in source control or not.
    
Now we can trigger a build.

```shell
./gradlew build
``` 


Once the build completes, a file `src/gdns/Simple.gdns` is generated. You can use `Simple.gdns` in Godot when assigning a script to a node.

![Attach Node Script](../assets/img/attach.png)

## Configuring target platforms

By default, the plugin configures the build to build all [supported platforms](supported-platforms.md). This can be changed via the `platforms` property of `godot`.

```kotlin
godot {
  platforms(Platform.WINDOWS)
}
```

## Running godot
The gradle plugin also provides a task that will download and run a specific version of Godot. By default, this version is set to the version of Godot this binding is built against. The version can be customized via the `godotVersion` property of the `godot` extension.

TBD