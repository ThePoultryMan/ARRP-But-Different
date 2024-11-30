plugins {
    id("dev.architectury.loom") version("1.7-SNAPSHOT")
    id("architectury-plugin") version("3.4-SNAPSHOT")
}

loom {
    mods {
        neoForge {
//            sourceSets.add(project(":neoforge").sourceSets.main.get())
        }
    }
}

architectury {
    platformSetupLoomIde()
    neoForge {
        platformPackage = "neoforge"
    }
}

val common by configurations.creating
val developmentNeoForge by configurations.existing

configurations {
    common
    neoForge
    compileClasspath {
        extendsFrom(common)
    }
    runtimeClasspath {
        extendsFrom(common)
    }
    developmentNeoForge {
        extendsFrom(common)
    }
}

dependencies {
    neoForge("net.neoforged:neoforge:${rootProject.extra.properties["neoforge_version"]}")

    implementation(project(path = ":neoforge", configuration = "namedElements")) { isTransitive = false }
    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    common(project(path = ":arrp_test_common", configuration = "namedElements")) { isTransitive = false }
}
