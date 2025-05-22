plugins {
    kotlin("jvm") version "2.1.10"
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