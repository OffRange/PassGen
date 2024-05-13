plugins {
    kotlin("jvm") version "1.9.23"
}

group = "de.davis.passgen"
version = "0.0.3-beta"

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
    jvmToolchain(21)
}