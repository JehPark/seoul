rootProject.name = "seoul"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("springLibs") {
            version("spring-boot-data", "2.7.1")

            library("boot-starter", "org.springframework.boot", "spring-boot-starter").withoutVersion()
            library("boot-web", "org.springframework.boot", "spring-boot-starter-web").withoutVersion()
            library("boot-devtools", "org.springframework.boot", "spring-boot-devtools").withoutVersion()
            library("boot-security", "org.springframework.boot", "spring-boot-starter-security").withoutVersion()
            library("boot-test", "org.springframework.boot", "spring-boot-starter-test").withoutVersion()
            library("boot-thymeleaf", "org.springframework.boot", "spring-boot-starter-thymeleaf").withoutVersion()
            library("boot-undertow", "org.springframework.boot", "spring-boot-starter-undertow").withoutVersion()

            library("boot-jdbc", "org.springframework.boot", "spring-boot-starter-jdbc").versionRef("spring-boot-data")
            library("boot-jpa", "org.springframework.boot", "spring-boot-starter-data-jpa").versionRef("spring-boot-data")
        }

        create("jacksonLibs") {
            version("jackson", "2.12.3")
            library("annotations", "com.fasterxml.jackson.core", "jackson-annotations").versionRef("jackson")
            library("kotlin", "com.fasterxml.jackson.module", "jackson-module-kotlin").versionRef("jackson")
        }

        create("libs") {
            library("arrow", "io.arrow-kt", "arrow-core").version("0.13.1")
            version("retrofit", "2.9.0")

            library("retrofit", "com.squareup.retrofit2", "retrofit").versionRef("retrofit")
            library("retrofit-jackson", "com.squareup.retrofit2", "converter-jackson").versionRef("retrofit")
            library("retrofit-mock", "com.squareup.retrofit2", "retrofit-mock").versionRef("retrofit")

            library("h2db", "com.h2database", "h2").version("1.3.148")
        }
    }
}

include(":paris")
include(":seoul-vixindex")
include(":seoul-external-service")
include(":seoul-infra")
include(":seoul-commons-dao")
include(":seoul-commons-model")
include(":tokyo")
