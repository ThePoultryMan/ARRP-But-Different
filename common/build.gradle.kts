architectury {
    common(rootProject.properties["enabled_platforms"].toString().split(","))
}

dependencies {
    // We depend on Fabric Loader here to use the Fabric @Environment annotations,
    // which get remapped to the correct annotations on each platform.
    // Do NOT use other classes from Fabric Loader.
    modImplementation("net.fabricmc:fabric-loader:${rootProject.properties["fabric_loader_version"]}")

    modApi("fuzs.forgeconfigapiport:forgeconfigapiport-common-neoforgeapi:${property("forge_config_api_port_version")}")
}
