plugins {
  id(BuildPlugins.ANDROID_LIBRARY)
  id(BuildPlugins.KOTLIN_ANDROID)
  id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
  id(BuildPlugins.KOTLIN_KAPT)
  id(BuildPlugins.KOTLIN_SERIALIZATION)
}

android {
  compileSdkVersion(ProjectProperties.COMPILE_SDK)

  defaultConfig {
    minSdkVersion(ProjectProperties.MIN_SDK)
    targetSdkVersion(ProjectProperties.TARGET_SDK)
    versionCode = 1
    versionName = "1.0.0"
    javaCompileOptions {
      annotationProcessorOptions {
        arguments["room.incremental"] = "true"
        arguments["room.schemaLocation"] = "$projectDir/schemas"
      }
    }

    buildConfigField("String", "BASE_URL", "\"https://medikate.org/hellolyfhmis/\"")
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }

}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  api(Lib.Kotlin.KT_STD)
  api(Lib.Kotlin.KTX_CORE)

  // Networking
  api(Lib.Networking.RETROFIT)
  api(Lib.Networking.CONVERTER_GSON)
  api(Lib.Networking.LOGGING_INTERCEPTOR)
  debugApi(Lib.Networking.CHUCKER)
  releaseApi(Lib.Networking.CHUCKER_RELEASE)

  // Serialization
  implementation(Lib.Networking.KOTLINX_JSON)
  implementation(Lib.Networking.RETROFIT_KOTLIN_SERIALIZATION)

  // Coroutines
  api(Lib.Async.COROUTINES)
  api(Lib.Async.COROUTINES_ANDROID)

  // Database
  api(Lib.Database.ROOM)
  kapt(Lib.Database.ROOM_DATABASE_COMPILER)
  api(Lib.Database.ROOM_KTX)
  api(Lib.Database.STETHO)

  // Gson
  api(Lib.Networking.GSON)

  // Timber
  api(Lib.Logger.TIMBER)

  // Testing
  testImplementation(TestLib.JUNIT)
}
