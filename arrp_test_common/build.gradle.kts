dependencies {
    modImplementation("net.fabricmc:fabric-loader:${rootProject.properties["fabric_loader_version"]}")
    implementation(project(path = ":common", configuration = "namedElements"))
}

architectury {
    common(rootProject.properties["enabled_platforms"].toString().split(","))
}
