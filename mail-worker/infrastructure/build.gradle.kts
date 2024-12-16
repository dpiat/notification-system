plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    // This
    implementation(project(":api"))
    implementation(project(":core"))

    // Base
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Notification System
    implementation("com.dpiataikin.microservice.common:event:unspecified")

    // Mapstruct
    implementation("org.mapstruct:mapstruct")
    kapt("org.mapstruct:mapstruct-processor")

    // Database
    runtimeOnly("io.r2dbc:r2dbc-postgresql")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.springframework:spring-jdbc")
}
