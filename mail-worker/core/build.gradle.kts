plugins {
    kotlin("jvm")
}

dependencies {
    // Reactor
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    // Logging
    implementation("org.apache.logging.log4j:log4j-api")
    implementation("org.apache.logging.log4j:log4j-to-slf4j")
}
