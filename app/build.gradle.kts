plugins {
    id("com.android.application")
    kotlin("plugin.serialization") version "1.5.30"
    kotlin("android")
    kotlin("kapt")
}
android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.compose"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    implementation("com.google.firebase:firebase-auth-ktx:21.0.7")
    implementation("com.google.firebase:firebase-database-ktx:20.0.5")

    implementation(Dependencies.Compose.livedata)
    implementation("com.google.dagger:hilt-android:2.41")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.1")
    implementation("androidx.core:core-ktx:1.8.0")
    implementation(Dependencies.Compose.navigation)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.prev)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("com.google.firebase:firebase-database:20.0.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
//    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
}
