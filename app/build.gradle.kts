plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("kotlin-kapt")


    id("kotlin-parcelize")
    kotlin("kapt")

    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.pokemon.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pokemon.app"
        minSdk = 24
        targetSdk = 34
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
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    //implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //    Hilt Dependency Injection
    implementation ("com.google.dagger:hilt-android:2.49")

    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    //implementation(libs.hilt.android)
    kapt("com.google.dagger:hilt-android-compiler:2.49")

    implementation ("com.google.code.gson:gson:2.10")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.3")

    // Navigation
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    //Facebook image load
    implementation ("com.facebook.fresco:fresco:2.5.0")


    //Network
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.2")
    implementation ("com.squareup.okhttp3:okhttp-urlconnection:3.12.1")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // Multidex Support
    implementation ("androidx.multidex:multidex:2.0.1")

    implementation("androidx.compose.material3:material3-android:1.2.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation ("io.coil-kt:coil-compose:2.0.0")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")


}