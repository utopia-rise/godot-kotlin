![Kotlin GDNative Logo](https://imgur.com/dSL1Vch.png)

# Kotlin/Native wrapper for the Godot Game Engine

## Overview

This is a **Kotlin** language wrapper for the [**Godot**](https://godotengine.org/) game engine. It uses the [**GDNative**](https://godotengine.org/article/dlscript-here) utility to interact with **Godot**'s core api's. The wrapper provides you Godot API's as Kotlin classes, so you can write your game logic completely in Kotlin. It will be compiled into a dynamic library using [*Kotlin/Native*](https://kotlinlang.org/docs/reference/native-overview.html). It contains GDNative bindings which allows Godot core and Kotlin code interact with each other.
You don't have to worry about any binding logic. Just write your game scripts like you would for [GDScript](https://docs.godotengine.org/en/3.1/getting_started/scripting/gdscript/gdscript_basics.html) or [C#](https://docs.godotengine.org/en/3.1/getting_started/scripting/c_sharp/).

CI status: [![Build Status](https://travis-ci.com/utopia-rise/godot-kotlin.svg?branch=master)](https://travis-ci.com/utopia-rise/godot-kotlin)

## Getting started

Look into [**Getting started**](./GETTING_STARTED.md) section to get more information.

## API differences from GDScript

Look into [**API differences from GDScript**](./API_DIFFERENCES.md) section to get more information.

## Registering classes

Look into [**Registration**](./REGISTRATION.md) section to get more information.

## Compiling from sources

Look into [**Compiling from source**](COMPILING_FROM_SOURCE.md) section to get more information.

## Developer discussion

Ask questions and collaborate on Discord:
https://discord.gg/qSU2EQs

## Authors

All authors are indicated in [*CONTRIBUTORS*](https://github.com/utopia-rise/kotlin-godot-wrapper/graphs/contributors) section on **GitHub**.  
This repo is a successor of [**MrAkakuy's** kotlin bindings for godot](https://github.com/MrAkakuy/kotlin-godot-wrapper), who did a great work with his project. We really thank him, without him this project would not exist.

If you have any questions, issues or feature suggestions you can write an [*Issue* here](https://github.com/utopia-rise/kotlin-godot-wrapper/issues/new/choose).

[License](./LICENSE)
