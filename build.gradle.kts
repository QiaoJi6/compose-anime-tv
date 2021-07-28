plugins {
  id("com.diffplug.spotless").version(Versions.spotless)
}

buildscript {
  repositories {
    google()
  }
  dependencies {
    classpath(kotlin("gradle-plugin", version = Versions.Kotlin.lang))
    classpath("com.android.tools.build:gradle:7.1.0-alpha05")
  }
}

allprojects {
  configRepository()

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = Versions.Java.jvmTarget
      allWarningsAsErrors = true
      freeCompilerArgs = listOf(
        "-Xopt-in=kotlin.RequiresOptIn",
      )
    }
  }

  apply(plugin = "com.diffplug.spotless")
  spotless {
    kotlin {
      target("**/*.kt")
      targetExclude("$buildDir/**/*.kt", "bin/**/*.kt", "buildSrc/**/*.kt")
      ktlint(Versions.ktlint)
      // licenseHeaderFile(rootProject.file("spotless/license"))
    }
    kotlinGradle {
      target("*.gradle.kts")
      ktlint(Versions.ktlint)
    }
    java {
      target("**/*.java")
      targetExclude("$buildDir/**/*.java", "bin/**/*.java")
      // licenseHeaderFile(rootProject.file("spotless/license"))
    }
  }
}