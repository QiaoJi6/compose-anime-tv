pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
  }
}

rootProject.name = "compose-anime-tv"
include(":app", ":core")

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
