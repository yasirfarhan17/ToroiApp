object BuildPlugins {
    const val GRADLE_BUILD_TOOLS =
        "com.android.tools.build:gradle:${Versions.RootLibVersion.TOOLS_BUILD}"
    const val KOTLIN_GRADLE_PLUGIN =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.RootLibVersion.KOTLIN}"
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN_SERIALIZATION = "kotlinx-serialization"
}

object Lib {
    object Kotlin {
        const val KT_STD =
            "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.RootLibVersion.KOTLIN}"
        const val KTX_CORE = "androidx.core:core-ktx:${Versions.LibVersion.KTX_CORE}"
    }

    object Jwt {
        const val JSON_WEB_TOKEN = "io.jsonwebtoken:jjwt-api:${Versions.LibVersion.JWT}"
        const val JSON_WEB_TOKEN_1 = "io.jsonwebtoken:jjwt-impl:${Versions.LibVersion.JWT}"
        const val JSON_WEB_TOKEN_2 = "io.jsonwebtoken:jjwt-orgjson:${Versions.LibVersion.JWT}"
    }

    object Android {
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.LibVersion.CONSTRAINT_LAYOUT}"
        const val MATERIAL_DESIGN =
            "com.google.android.material:material:${Versions.LibVersion.MATERIAL_DESIGN}"
    }

    object Lifecycle {
        const val VIEW_MODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LibVersion.LIFECYCLE_KTX}"
        const val LIVE_DATA_KTX =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LibVersion.LIFECYCLE_KTX}"
        const val COMMON_JAVA8 =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.LibVersion.LIFECYCLE_KTX}"
    }

    object Database {
        const val ROOM = "androidx.room:room-runtime:${Versions.LibVersion.ROOM}"
        const val ROOM_DATABASE_COMPILER = "androidx.room:room-compiler:${Versions.LibVersion.ROOM}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versions.LibVersion.ROOM}"
        const val STETHO = "com.facebook.stetho:stetho:${Versions.DebugLibVersion.STETHO}"
    }

    object Async {
        const val COROUTINES =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.LibVersion.COROUTINES}"
        const val COROUTINES_ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.LibVersion.COROUTINES}"
    }

    object Networking {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.LibVersion.RETROFIT}"
        const val CONVERTER_GSON =
            "com.squareup.retrofit2:converter-gson:${Versions.LibVersion.RETROFIT}"
        const val LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.LibVersion.OK_HTTP}"
        const val CHUCKER = "com.github.chuckerteam.chucker:library:${Versions.LibVersion.CHUCKER}"
        const val CHUCKER_RELEASE =
            "com.github.chuckerteam.chucker:library-no-op:${Versions.LibVersion.CHUCKER}"
        const val GSON = "com.google.code.gson:gson:${Versions.LibVersion.GSON}"
        const val KOTLINX_JSON =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.LibVersion.KOTLINX_JSON}"
        const val RETROFIT_KOTLIN_SERIALIZATION =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.LibVersion.RETROFIT_KOTLIN_SERIALIZATION}"
    }

    object Logger {
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.LibVersion.TIMBER}"
    }

    object DI {
        const val DAGGER = "com.google.dagger:dagger:${Versions.LibVersion.DAGGER}"
        const val DAGGER_COMPILER =
            "com.google.dagger:dagger-compiler:${Versions.LibVersion.DAGGER}"
    }

    object Jitsi {
        const val JITSI = "org.jitsi.react:jitsi-meet-sdk:${Versions.LibVersion.JITSI}"
    }

    object Razorpay {
        const val RAZORPAY = "com.razorpay:checkout:${Versions.LibVersion.RAZORPAY}"
    }
}

object TestLib {
    const val JUNIT = "junit:junit:${Versions.TestLibVersion.JUNIT}"
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.TestLibVersion.ANDROID_JUNIT}"
}

object AndroidTestLib {
    const val ESPRESSO_CORE =
        "androidx.test.espresso:espresso-core:${Versions.TestLibVersion.ESPRESSO_CORE}"
}

object DebugLib {
    const val LEAK_CANARY =
        "com.squareup.leakcanary:leakcanary-android:${Versions.DebugLibVersion.LEAK_CANARY}"
}