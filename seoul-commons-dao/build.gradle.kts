plugins {
    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":seoul-commons-model"))

    implementation(springLibs.boot.starter)
    implementation(springLibs.boot.jdbc)

    implementation(jacksonLibs.kotlin)

    implementation(libs.h2db)
}