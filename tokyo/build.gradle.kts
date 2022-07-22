plugins {
    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":seoul-commons-model"))
    implementation(project(":seoul-commons-dao"))

    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(springLibs.boot.starter)
    implementation(springLibs.boot.web)
    implementation(springLibs.boot.validation)

    implementation(jacksonLibs.kotlin)

    implementation(libs.arrow)

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}