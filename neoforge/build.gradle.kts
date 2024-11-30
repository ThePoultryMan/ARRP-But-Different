plugins {
    id("architectury-plugin") version("3.4-SNAPSHOT")
    id("com.github.johnrengelman.shadow")
}

val neoforge_version: String by project
extra {
    neoforge_version
}

architectury {
    platformSetupLoomIde()
    neoForge()
}

repositories {
    maven {
        name = "NeoForged"
        url = uri("https://maven.neoforged.net/releases/")
    }
}

val common by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
}
configurations.getByName("compileClasspath") {
    extendsFrom(common)
}
configurations.getByName("runtimeClasspath") {
    extendsFrom(common)
}
configurations.getByName("developmentNeoForge") {
    extendsFrom(common)
}
val shadowBundle by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false;
}

dependencies {
    neoForge("net.neoforged:neoforge:${property("neoforge_version")}")

    common(project(path = ":common", configuration = "namedElements")) {
        isTransitive = false
    }
    shadowBundle(project(path = ":common", configuration = "transformProductionNeoForge"))
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("META-INF/neoforge.mods.toml") {
        expand(inputs.properties)
    }
}
