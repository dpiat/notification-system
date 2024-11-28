plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("kapt") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
}

val springBootVersion: String by project

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencyManagement {
        dependencies {
            dependency("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-data-redis:${springBootVersion}")
            dependency("org.springframework.boot:spring-boot-starter-webflux:${springBootVersion}")
            dependency("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.1")
            dependency("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.3")
            dependency("org.jetbrains.kotlin:kotlin-reflect:1.9.25")
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.8.1")
            dependency("org.springframework.kafka:spring-kafka:${springBootVersion}")
            dependency("org.projectlombok:lombok:1.18.36")
            dependency("org.mapstruct:mapstruct:1.5.3.Final")
            dependency("org.mapstruct:mapstruct-processor:1.5.3.Final")

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
