plugins {
    id("architectury-plugin") version("3.4-SNAPSHOT")
    id("com.gradleup.shadow") version("9.0.0-beta4")
}

architectury {
    platformSetupLoomIde()
    fabric()
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
configurations.getByName("developmentFabric") {
    extendsFrom(common)
}
val shadowCommon by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false;
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

    modApi("fuzs.forgeconfigapiport:forgeconfigapiport-fabric:${property("forge_config_api_port_version")}")

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowCommon(project(path = ":common", configuration = "transformProductionFabric")) { isTransitive = false }
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("META-INF/neoforge.mods.toml") {
        expand(inputs.properties)
    }
}
