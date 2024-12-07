plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("com.dpiataikin.microservice.common:response:unspecified")
    implementation("com.dpiataikin.microservice.common:event:unspecified")
    implementation("org.mapstruct:mapstruct")
    kapt("org.mapstruct:mapstruct-processor")
}
