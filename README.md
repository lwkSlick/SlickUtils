# SlickUtils

A Fabric client-side mod for Minecraft 1.21.11 that bundles a bunch of small, independent QoL, HUD, and PvP utility features into one mod with a single unified config screen — instead of installing a dozen separate small mods (à la Lunar/Feather feature bloat, minus the bloat).

## Why

Big all-in-one clients like Lunar Client and Feather Client ship hundreds of features, but most players only ever use one or two. SlickUtils flips that: a curated, growing set of small toggleable features, each independent, all configured from one YACL screen accessible through ModMenu. No per-mod configs, no per-mod keybind conflicts, no installing 10 jars for 10 small tweaks.

## Features

_Work in progress — features will be listed here as they're added._

## Requirements

- Minecraft 1.21.11
- Fabric Loader
- Fabric API
- YetAnotherConfigLib (YACL) v3
- Mod Menu (optional, for accessing config in-game)

## Building

Standard Fabric Loom build:

```
./gradlew build
```

Output jar will be in `build/libs/`.

## License

MIT — see [LICENSE](LICENSE).
