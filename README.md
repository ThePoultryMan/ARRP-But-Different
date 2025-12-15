# What is this branch?

Due to the renaming `ResourceLocation` to `Identifier` in the 1.21.11 update, using Stonecutter to
manage the changes between the pre-1.21.11 versions and modern versions would result in incredibly
dense and verbose code. To avoid this, this branch `1.21.10` is the "legacy" code that may or may
not have changes backported, while the `main` branch will continue to track with the current
Minecraft version.

# Advanced Runtime Resource Packs but it's Different but it's Still ARRP

ARRP But Different allows developers to dynamically create assets and data at runtime, allowing
developers to not have to pack extreme amounts of resources into mods for things such as dynamic,
mod-dependent, item generation.

### Note

ARRP But Different is a fork of the [ARRP](https://modrinth.com/mod/arrp) mod. This mod has made
changes to the system ARRP used, and also supports the NeoForge mod loader.

## Status

ARRP But Different is still in a beta state. Please report any issues and bugs you find.

### Support

Supports the same generators that the original ARRP mod did, with some small syntax differences.

### Events

Fabric supports all events. NeoForge _only_ supports "BeforeUser" currently.

## FAQ

### Is this compatible with resource packs?

Yes, just like the original ARRP mod, this mod creates "fake" resource packs that are the second to
last in priority. This allows any other resource pack to override the resource packs created through
this mod.

However, this means that Runtime Resource Packs (RRPs) cannot override resources created by other
mods, but can still override Minecraft's resources.

### Is this compatible with the original ARRP

No, this mod makes changes to the syntax and systems that ARRP used.

## NeoForge Support

This mod also supports NeoForge alongside Fabric, but there is one small piece that does not translate
nicely between the two loaders. The NeoForge version does not support all the entrypoints that the
Fabric version does due to the way mod loading works.

## Using ARRP But Different

For information on how to use ARRP But Different, refer to the [docs](https://arrp-but-different.readthedocs.io/en/latest/).

For resource pack creators looking to add support for generated resources, refer to the [resource
dumping page](https://arrp-but-different.readthedocs.io/en/latest/dumping-resources).
