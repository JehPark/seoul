plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":seoul-vixindex"))
    implementation(project(":seoul-infra"))

    implementation(libs.arrow)
    implementation(libs.retrofit)
    implementation(libs.retrofit.jackson)

    implementation(jacksonLibs.kotlin)
}