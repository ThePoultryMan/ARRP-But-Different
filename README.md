# Advanced Runtime Resource Packs but it's Different but it's Still ARRP

ARRP But Different allows developers to dynamically create assets and data at runtime, allowing
developers to not have to pack extreme amounts of resources into mods for things such as dynamic,
mod-dependent, item generation.

### Note

ARRP But Different is a fork of the [ARRP](https://modrinth.com/mod/arrp) mod. This mod has made
changes to the system ARRP used, and also supports the NeoForge mod loader.

## FAQ

### Is this compatible with resource packs?

Yes, just like the original ARRP mod, this mod creates fakes resource packs that are the second to
last in priority. This allows any other resource pack to override the resource packs created through
this mod.

However, this means that Runtime Resource Packs (RRPs) cannot override resources created by other
mods, but can still override Minecraft's resources.

### Is this compatible with the original ARRP

No, this mod makes changes to the syntax and systems that ARRP used. Fortunately, migrating from ARRP
is rather straightforward.

## NeoForge Support

This mod also supports NeoForge alongside Fabric, but there is one small piece that does not translate
nicely between the two loaders. The NeoForge version does not support all the entrypoints that the
Fabric version does due to the way mod loading works.

## Using ARRP But Different

For information on how to use ARRP But Different, refer to the [wiki](https://github.com/ThePoultryMan/ARRP-But-Different/wiki).

For resource pack creators looking to add support for generated resources, refer to the [resource
dumping page](https://github.com/ThePoultryMan/ARRP-But-Different/wiki/Dumping-Resources).
