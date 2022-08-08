plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.7.10"
    id("com.google.gms.google-services") version "4.3.13"
}
repositories {
    google()
    mavenCentral()
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
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs =
            listOf("-Xjvm-default=enable")

    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.kotlinCompilerExtensionVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.Firebase.firebaseBom)
    implementation(Dependencies.Firebase.firebaseAuth)
    implementation(Dependencies.Firebase.firebaseDatabase)

    implementation(Dependencies.Livedata.livedata)
    implementation(Dependencies.Livedata.lifecycle)

    implementation(Dependencies.Hilt.daggerHilt)

    implementation(Dependencies.Coroutines.coroutinesCore)
    implementation(Dependencies.Coroutines.coroutinesAndroid)
    implementation(Dependencies.Coroutines.coroutinesServ)

    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)

    implementation(Dependencies.Navigation.navigation)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.prev)
    implementation(Dependencies.Compose.activityComp)

    implementation(Dependencies.Lifecycle.lifecycle)
    implementation(Dependencies.Lifecycle.viewModel)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Compose.uiTest)
    debugImplementation(Dependencies.Compose.toolingTest)
}