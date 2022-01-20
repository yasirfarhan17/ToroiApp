// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  repositories {
    google()
    jcenter()
  }
  dependencies {
    classpath(BuildPlugins.GRADLE_BUILD_TOOLS)
    classpath(BuildPlugins.KOTLIN_GRADLE_PLUGIN)
    classpath(kotlin("serialization", version = Versions.RootLibVersion.KOTLIN))

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle.kts files
  }
}

allprojects {
  repositories {
    google()
    jcenter()
    maven(url = "https://github.com/jitsi/jitsi-maven-repository/raw/master/releases")
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
