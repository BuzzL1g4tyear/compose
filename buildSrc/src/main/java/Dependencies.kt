object Dependencies {

    const val kotlinCompilerExtensionVersion = "1.0.5"

    object Compose {
        const val version = "1.1.1"
        private const val compose_version = "1.2.0"

        const val ui = "androidx.compose.ui:ui:$compose_version"
        const val material = "androidx.compose.material:material:$compose_version"
        const val prev = "androidx.compose.ui:ui-tooling-preview:$compose_version"
        const val activityComp = "androidx.activity:activity-compose:1.5.1"

        const val toolingTest = "androidx.compose.ui:ui-tooling:$version"
        const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
    }

    object Navigation {
        private const val nav_version = "2.5.1"

        const val navigation = "androidx.navigation:navigation-compose:$nav_version"
    }

    object Livedata {
        private const val livedataVersion = "1.2.0"
        private const val lifecycleVersion = "2.5.1"

        const val livedata = "androidx.compose.runtime:runtime-livedata:$livedataVersion"
        const val lifecycle = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    }

    object Firebase {
        const val firebaseBom = "com.google.firebase:firebase-bom:30.3.1"
        const val firebaseAuth = "com.google.firebase:firebase-auth-ktx:21.0.7"
        const val firebaseDatabase = "com.google.firebase:firebase-database-ktx:20.0.5"
    }

    object Lifecycle {
        private const val lifecycle_version = "2.5.1"

        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    }

    object Coroutines {
        private const val coroutines_version = "1.6.4"

        const val coroutinesServ =
            "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutines_version"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    }

    object Hilt {
        private const val daggerHilt_version = "2.41"

        const val daggerHilt = "com.google.dagger:hilt-android:$daggerHilt_version"
    }

    object Android {

        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val appCompat = "androidx.appcompat:appcompat:1.4.2"
        const val material = "com.google.android.material:material:1.6.1"
    }

    object Test {
        const val jUnit = "junit:junit:4.+"
        const val androidJUnit = "androidx.test.ext:junit:1.1.2"
        const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
    }
}