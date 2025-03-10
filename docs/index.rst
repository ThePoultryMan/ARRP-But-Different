=================================================================================================
Advanced Runtime Resource Packs but it's different but it's Still Advanced Runtime Resource Packs
=================================================================================================

ARRP But Different allows developers to dynamically create assets and data at runtime, allowing
developers to not have to pack extreme amounts of resources into mods for things such as dynamic,
mod-dependent, item generation.

.. note:: This is a Fork

    ARRP But Different is a fork of the `ARRP <https://modrinth.com/mod/arrp>`_ mod. This mod has
    made changes to the system ARRP used, and supports ≥1.21.4 and NeoForge.

Status
======

ARRP But Different is still in a beta state. Please report any issues and bugs you find.

Support
-------

Supports the same generators that the original ARRP mod did, with some small syntax differences.

Events
------

Fabric supports all events. NeoForge *only* supports "BeforeUser" currently.

FAQ
===

Is this compatible with resource packs?
---------------------------------------

Yes, just like the original ARRP mod, this mod creates "fake" resource packs that are the second to
last in priority. This allows any other resource pack to override the resource packs created through
this mod.

However, this means that Runtime Resource Packs (RRPs) cannot override resources created by other
mods, but can still override Minecraft's resources.

Is this compatible with the original ARRP
-----------------------------------------

No, this mod makes changes to the syntax and systems that ARRP used.

NeoForge Support
================

This mod also supports NeoForge alongside Fabric, but there is one small piece that does not translate
nicely between the two loaders. The NeoForge version does not support all the entrypoints that the
Fabric version does due to the way mod loading works.

Contents
========

.. toctree::
    :maxdepth: 2
    :caption: About

    index
    getting-started
    events

.. toctree::
    :maxdepth: 2
    :caption: Usage

    /usage/lang
