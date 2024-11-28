plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "notification-service"

include("api")
include("application")
include("core")
include("infrastructure")
