import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    id("dev.architectury.loom") version("1.7-SNAPSHOT") apply(false)
    id("architectury-plugin") version("3.4-SNAPSHOT")
    id("com.github.johnrengelman.shadow") version("8.1.1")
    id("maven-publish")
}

architectury {
    minecraft = rootProject.properties["minecraft_version"].toString()
}

allprojects {
    group = rootProject.properties["maven_group"].toString()
    version = rootProject.properties["mod_version"].toString()
}

subprojects {
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")
    apply(plugin = "maven-publish")

    repositories {
        maven {
            url = uri("https://maven.neoforged.net/releases/")
        }
        maven {
            name = "Fuzs Mod Resources"
            url = uri("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
        }
    }

    val minecraft by configurations.existing
    val mappings by configurations.existing

    val loom = project.extensions.getByType<LoomGradleExtensionAPI>()

    dependencies {
        minecraft("net.minecraft:minecraft:${rootProject.properties["minecraft_version"]}")
        mappings(loom.officialMojangMappings())
    }

    publishing {
        publications {
            register("mavenJava", MavenPublication::class) {
                artifactId = rootProject.properties["archives_name"].toString();
                from(components.getByName("java"))
            }

            repositories {}
        }
    }
}
