plugins {
    kotlin("jvm") version "2.3.0"
    id("com.gradleup.shadow") version "9.3.0"
}

group = "com.txnnor"
version = "1.0.0"

repositories {
    mavenCentral()
    maven(url = "https://maven.hytale.com/release")
    maven(url = "https://maven.hytale.com/pre-release")
}

dependencies {
    compileOnly("com.hypixel.hytale:Server:2026.02.11-255364b8e")
}

kotlin {
    jvmToolchain(25)
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

tasks.shadowJar {
    archiveClassifier.set("")
    archiveVersion.set("")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from("src/main/resources")
    mergeServiceFiles()
}