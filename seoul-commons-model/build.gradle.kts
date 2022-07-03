plugins {
    kotlin("plugin.jpa") version "1.4.32"
    kotlin("jvm")
}

dependencies {
    implementation(springLibs.boot.jpa)

    implementation(jacksonLibs.kotlin)
}