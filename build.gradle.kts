import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

	buildscript {
		repositories {
			mavenCentral()
		}
	}

plugins {
	id("org.springframework.boot") version "2.7.0" apply false
	id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
	kotlin("jvm") version "1.6.21" apply false
	kotlin("plugin.spring") version "1.6.21" apply false
}

allprojects {
	group = "com.example"
	version = "1.0.0"

	tasks.withType<JavaCompile> {
		sourceCompatibility = "1.8"
		targetCompatibility = "1.8"
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}
}

subprojects {
	repositories {
		mavenCentral()
	}

	apply {
		plugin("io.spring.dependency-management")
	}
}