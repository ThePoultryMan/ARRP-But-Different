plugins {
    id("com.gradleup.shadow") version ("9.0.0-beta4")
}

architectury {
    platformSetupLoomIde()
    fabric()
}

val common by configurations.creating
configurations.getByName("compileClasspath") {
    extendsFrom(common)
}
configurations.getByName("runtimeClasspath") {
    extendsFrom(common)
}
configurations.getByName("developmentFabric") {
    extendsFrom(common)
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

    implementation(project(path = ":fabric", configuration = "namedElements")) { isTransitive = false }
    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    common(project(path = ":arrp_test_common", configuration = "namedElements")) { isTransitive = false }
}
