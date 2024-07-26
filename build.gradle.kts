plugins {
    java
    id("fabric-loom") version "1.7-SNAPSHOT"
}

class ModInfo {
    val id = property("mod.id").toString()
    val group = property("mod.group").toString()
    val version = property("mod.version").toString()
}

val mod = ModInfo()
val loaderVersion = property("deps.fabric_loader").toString()

loom {
    accessWidenerPath = file("src/main/resources/globalize.accesswidener")
}

dependencies {
    minecraft("com.mojang:minecraft:${property("deps.minecraft")}")
    mappings("net.fabricmc:yarn:${property("deps.yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${loaderVersion}")

    modImplementation(fabricApi.module("fabric-api-base", property("deps.fabric_api").toString()))
    include(fabricApi.module("fabric-api-base", property("deps.fabric_api").toString()))
}


tasks.processResources {
    inputs.property("id", mod.id)
    inputs.property("version", mod.version)

    val map = mapOf(
        "id" to mod.id,
        "version" to mod.version
    )

    filesMatching("fabric.mod.json") { expand(map) }
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
