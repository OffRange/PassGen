plugins {
    kotlin("jvm")
}

group = "de.davis.passgen.demo"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":"))
}

kotlin {
    jvmToolchain(17)
}