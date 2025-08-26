import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("dev.isxander.modstitch.base") version "0.7.0-unstable"
    id("me.modmuss50.mod-publish-plugin") version("0.8.4")
}

fun prop(name: String, consumer: (prop: String) -> Unit) {
    (findProperty(name) as? String?)
        ?.let(consumer)
}

val minecraft = property("deps.minecraft") as String
val testing = if (property("testing") != null) {
    property("testing").toString() == "true"
} else {
    false
}

if (testing) {
    sourceSets.main {
        java.srcDir("src/testMod/java")
    }
}

modstitch {
    minecraftVersion = minecraft

    // If parchment doesnt exist for a version yet you can safely
    // omit the "deps.parchment" property from your versioned gradle.properties
    parchment {
        prop("deps.parchment") { mappingsVersion = it }
    }

    // This metadata is used to fill out the information inside
    // the metadata files found in the templates folder.
    metadata {
        modId = "advanced_runtime_resource_packs_but_different"
        prop("mod.name") { modName = it }
        modDescription = "A mod that allows mods to generate assets/data on the fly, but it's (slightly) different, and also on NeoForge."
        prop("mod.version") { modVersion = it }
        modGroup = "io.github.thepoultryman"
        modAuthor = "ThePoultryMan"

        fun <K, V> MapProperty<K, V>.populate(block: MapProperty<K, V>.() -> Unit) {
            block()
        }

        replacementProperties.populate {
            // You can put any other replacement properties/metadata here that
            // modstitch doesn't initially support. Some examples below.
            put("mod_issue_tracker", "https://github.com/ThePoultryMan/ARRP-But-Different")
            prop("deps.forge_config_api_port") {
                put("forge_config_api_port_version", it)
            }

            put("test_mod_block", if (testing) {
                if (modstitch.isLoom) {
                    ",\"io.github.thepoultryman.arrp_but_testing.ARRPButTestingFabric\""
                } else {
                    "[[mods]]\nmodId = \"arrp_but_testing\""
                }
            } else {
                ""
            })
        }
    }

    // Fabric Loom (Fabric)
    loom {
        // It's not recommended to store the Fabric Loader version in properties.
        // Make sure its up to date.
        fabricLoaderVersion = "0.16.14"

        // Configure loom like normal in this block.
        configureLoom {}
    }

    // ModDevGradle (NeoForge, Forge, Forgelike)
    moddevgradle {
        prop("deps.neoforge") { neoForgeVersion = it }

        // Configures client and server runs for MDG, it is not done by default
        defaultRuns()
    }

    mixin {
        // You do not need to specify mixins in any mods.json/toml file if this is set to
        // true, it will automatically be generated.
        addMixinsToModManifest = true

        configs.register("advanced_runtime_resource_packs_but_different")
    }
}

// Stonecutter constants for mod loaders.
// See https://stonecutter.kikugie.dev/stonecutter/guide/comments#condition-constants
var constraint: String = name.split("-")[1]
stonecutter {
    constants += arrayOf(
        "fabric" to constraint.equals("fabric"),
        "neoforge" to constraint.equals("neoforge"),
        "forge" to constraint.equals("forge"),
        "vanilla" to constraint.equals("vanilla")
    )
}

repositories {
    maven {
        name = "Fuzs Mod Resources"
        url = uri("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
    }
}

// All dependencies should be specified through modstitch's proxy configuration.
// Wondering where the "repositories" block is? Go to "stonecutter.gradle.kts"
// If you want to create proxy configurations for more source sets, such as client source sets,
// use the modstitch.createProxyConfigurations(sourceSets["client"]) function.
dependencies {
    if (modstitch.isLoom) {
        modstitchModImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")

        modstitchModApi("fuzs.forgeconfigapiport:forgeconfigapiport-fabric:${property("deps.forge_config_api_port")}")
    } else {
//        modstitchModImplementation(sourceSets.test.get().output)
    }

    // Anything else in the dependencies block will be used for all platforms.
}

publishMods {
    if (modstitch.isLoom) {
        file.set(tasks.named<RemapJarTask>("remapJar").get().archiveFile)
    } else {
        file.set(tasks.jar.get().archiveFile)
    }

    var minMinecraftVersion = findProperty("deps.minecraft_min") as String?
    var versionRange = if (minMinecraftVersion != null) {
        "${minMinecraftVersion}-${minecraft}"
    } else {
        minecraft
    }
    var loader = if (modstitch.isLoom) {
        "fabric"
    } else {
        "neoforge"
    }
    displayName = "${property("mod.name")} ${property("mod.version")}-${loader} for $versionRange"
    version = "${property("mod.version")}+${minecraft}-${loader}"
    type = BETA
    if (modstitch.isLoom) {
        modLoaders.addAll("fabric", "quilt")
    } else {
        modLoaders.add("neoforge")
    }
    changelog = rootProject.file("CHANGELOG.md").readText()

    modrinth {
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        projectId = "5AA9oDBl"

        projectDescription.set(providers.fileContents(layout.projectDirectory.file("README.md")).asText)

        if (minMinecraftVersion != null) {
            minecraftVersionRange {
                start = minMinecraftVersion
                end = minecraft
            }
        } else {
            minecraftVersions.add(minecraft)
        }

        if (modstitch.isLoom) {
            requires("fabric-api")
            requires("forge-config-api-port")
        }
    }

    curseforge {
        accessToken = providers.environmentVariable("CURSEFORGE_API_KEY")
        projectId = "1232883"

        if (minMinecraftVersion != null) {
            minecraftVersionRange {
                start = minMinecraftVersion
                end = minecraft
            }
        } else {
            minecraftVersions.add(minecraft)
        }

        if (modstitch.isLoom) {
            requires("fabric-api")
            requires("forge-config-api-port-fabric")
        }
    }
}
