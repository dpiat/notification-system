plugins {
    id("maven-publish")
    kotlin("jvm")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("com.dpiataikin.microservice.common:response:unspecified")
    implementation("com.dpiataikin.microservice.common:event:unspecified")
    implementation("org.mapstruct:mapstruct")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "${project.group}"
            artifactId = "${project.name}"
            version = "${project.version}"
            from(components["kotlin"])
        }
    }
}
