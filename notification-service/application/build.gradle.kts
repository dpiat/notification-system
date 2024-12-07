plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.cloud.tools.jib")
    kotlin("plugin.spring")
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(":api"))
    implementation(project(":core"))
    implementation(project(":infrastructure"))
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("com.dpiataikin.microservice.common:response")
    implementation("com.dpiataikin.microservice.common:event:unspecified")
    implementation("com.dpiataikin.microservice.common:util")
    implementation("org.mapstruct:mapstruct")
    kapt("org.mapstruct:mapstruct-processor")
}

val dockerImage: String by project

jib {
    to {
        image = "notification-system/${project.name}"
        tags = setOf("${project.version}", "latest")
        auth {
            username = System.getenv("DOCKER_REGISTRY_USERNAME") ?: "default"
            password = System.getenv("DOCKER_REGISTRY_PASSWORD") ?: "default"
        }
    }

    from {
        image = dockerImage
    }

    container {
        mainClass = "com.dpiataikin.notificationservice.application.AppKt"
        ports = listOf("8080")
        environment = mapOf(
            "SOME_KEY" to "some_value",
            "KEY" to "Value"
        )
        jvmFlags = listOf("-Xms512m", "-Xmx1024m") // JVM options for the container
        creationTime = "USE_CURRENT_TIMESTAMP"
        extraClasspath
    }
}