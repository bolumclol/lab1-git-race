// Adds Kotlin compile task to the Gradle build
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Adds Spring Boot and kotlin plugins
plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.30"
    kotlin("plugin.spring") version "1.5.30"

    // Plugins for persistence
    kotlin("plugin.jpa") version "1.5.30"
    kotlin("plugin.allopen") version "1.5.30"
}

allOpen {
  annotation("javax.persistence.Query")
  annotation("javax.persistence.Entity")
  annotation("javax.persistence.Embeddable")
  annotation("javax.persistence.MappedSuperclass")
}

// Sets groupID and version of the task
group = "es.unizar.webeng"
version = "0.0.1-SNAPSHOT"

// Sets remote dependency repository to Maven Central
repositories {
    mavenCentral()
}

// Declares dependencies for implementation and testing
dependencies {
    // Kotlin plugin managed version dependencies
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Spring plugin managed version dependencies
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-freemarker")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Enables spring actuator. Required for metrics export.
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    runtimeOnly("org.springframework.boot:spring-boot-devtools")

    runtimeOnly("com.h2database:h2")

    // Enables exporting metrics in /actuator/prometheus
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("com.google.code.gson:gson")

    // Dependencies with explicit version number
    implementation("org.webjars:bootstrap:5.1.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.json:json:20171018")

    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("io.springfox:springfox-swagger2:2.9.2")

}

// Makes Kotlin use the JVM 11 toolchain
kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11))
    }
}

// Sets compiler options to generetate errors with
// null pointers and target version 11 of JVM
val compileKotlin: KotlinCompile by tasks
with(compileKotlin.kotlinOptions) {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
}

// Configure tests to run with the JUnit testing framework
val test: Test by tasks
test.useJUnitPlatform()