plugins {
    id("org.springframework.boot") version "3.4.0" apply false
    id("io.spring.dependency-management") version "1.1.6"
    id("com.google.cloud.tools.jib") version "3.4.4" apply false
    kotlin("plugin.spring") version "1.9.25"
    kotlin("jvm") version "1.9.25"
    kotlin("kapt") version "1.9.25"
}

val springBootVersion: String by project

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")

    group = "com.dpiataikin.notifications-system.mail-worker"
    version = "0.0.1"

    repositories {
        mavenCentral()
        mavenLocal()
        maven {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/dpiat/microservice-common-java")
            credentials {
                username = System.getenv("GITHUB_REGISTRY_USERNAME")
                password = System.getenv("GITHUB_REGISTRY_PASSWORD")
            }
        }
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencyManagement {
        dependencies {
            // Base
            dependency("org.springframework.boot:spring-boot-starter-data-redis:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-webflux:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-data-r2dbc:${springBootVersion}")
            dependency("org.springframework.kafka:spring-kafka:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-mail:${springBootVersion}")
            dependency("org.springframework:spring-jdbc:5.3.23")
            dependency("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.1")
            dependency("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.3")
            dependency("org.jetbrains.kotlin:kotlin-reflect:1.9.25")
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.8.1")

            // Notification System
            dependency("com.dpiataikin.microservice.common:event:unspecified")
            dependency("com.dpiataikin.microservice.common:response:unspecified")
            dependency("com.dpiataikin.microservice.common:util:unspecified")
            dependency("com.dpiataikin.microservice.common:usecase:unspecified")

            // Mapstruct
            dependency("org.mapstruct:mapstruct:1.5.3.Final")
            dependency("org.mapstruct:mapstruct-processor:1.5.3.Final")

            // Database
            dependency("io.r2dbc:r2dbc-postgresql:0.8.10.RELEASE")
            dependency("org.postgresql:postgresql:42.5.0")
            dependency("org.flywaydb:flyway-core:9.6.0")

            // Logging
            dependency("org.apache.logging.log4j:log4j-api:2.23.1")
            dependency("org.apache.logging.log4j:log4j-to-slf4j:2.23.1")

            // Notification System
            dependency("com.dpiataikin.notifications-system.notification-service:api:0.0.1")
            //testImplementation("org.springframework.boot:spring-boot-starter-test")
            //testImplementation("io.projectreactor:reactor-test")
            //testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
            //testImplementation("org.springframework.kafka:spring-kafka-test")
            //testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
