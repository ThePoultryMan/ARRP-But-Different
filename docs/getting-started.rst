===============
Getting Started
===============

Add Maven Repository
====================

Regardless if you're using Fabric, NeoForge, or Architectury, you need to add
the Modrinth maven to your project's *build.gradle*.

In the appropriate build.gradle:

.. code-block:: groovy

    repositories {
        // ...
        exclusiveContent {
            forRepository {
                maven {
                    name = "Modrinth"
                    url = "https://api.modrinth.com/maven"
                }
            }
            filter {
                includeGroup "maven.modrinth"
            }
        }
    }

.. note:: Repository Declaration

    By adding the Modrinth maven in this way, you're telling gradle to only look
    for dependencies with the group "maven.modrinth" in the Modrinth maven.

    A regular declaration would look like this:

    .. code-block:: groovy

        repositories {
            // ...
            maven {
                name = "Modrinth" // optional
                url = "https://api.modrinth.com/maven"
            }
        }

    You can read more about the Modrinth maven `here <https://support.modrinth.com/en/articles/8801191-modrinth-maven>`_.

Then, declare the dependency:

.. code-block:: groovy

    dependencies {
        // ...
        modImplementation "maven.modrinth:arrp-but-different:VERSION"
    }

.. tip:: Version Selection

    You can find the appropriate version on the `Modrinth page <https://modrinth.com/project/arrp-but-different/versions>`_.

Creating Packs
==============

For both Fabric and NeoForge you need to register an event listener, but the process is a bit different
depending on the loader.

.. important:: Mappings

    All code examples are in mojmap.

.. tip:: Which Event to Use

    See :doc:`Events </events>` for more more information.

Fabric
------

To add a new pack on Fabric, you need to register a listener, in your mod initializer, through the
ARRPEvent class.

See the example below:

.. code-block:: java

    public class ARRPExample implements ModInitializer {
        @Override
        public void onInitialize() {;
            ARRPEvent.BEFORE_USER.register((resourcePacks) -> {
                // Creates the resource pack
                RuntimeResourcePack pack = RuntimeResourcePack.create(Identifier.fromNamespaceAndPath("mod_id", "example_pack"));
                // Adds the resource pack to the list. If this is not done, your resource pack will never be used and will not load.
                resourcePacks.add(pack);
            }));
        }
    }

NeoForge
--------

To add a new pack on NeoForge, you need to register a listener through the ARRPForNeoForge.ARRP_EVENT_BUS
static variable.

See the example below:

.. code-block:: java

    @Mod("mod_id")
    public class ARRPExample {
        @Override
        public void onInitialize() {;
            ARRPForNeoForge.ARRP_EVENT_BUS.register((ARRPNeoForgeEvent.BeforeUser) -> {
                // Creates the resource pack
                RuntimeResourcePack pack = RuntimeResourcePack.create(Identifier.fromNamespaceAndPath("mod_id", "example_pack"));
                // Adds the resource pack to the list. If this is not done, your resource pack will never be used and will not load.
                event.addPack(pack);
            }));
        }
    }

Use The Runtime Resource Pack
=============================

The RuntimeResourcePack interface provides numerous ways to add resources and data to the pack. The
following pages document such methods.

.. warning:: This list is incomplete!
