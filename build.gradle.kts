plugins {
    kotlin("jvm") version "2.2.20"
}

group = "de.davis.passgen"
version = "0.1.0-beta"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}