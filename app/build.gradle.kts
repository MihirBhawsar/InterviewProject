plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)

}

android {
    namespace = "com.example.demo2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.demo2"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.cardview)
    implementation(libs.recyclerview)
    implementation(libs.sdp)
    implementation(libs.ssp)
    implementation(libs.glide)
    implementation(libs.compiler)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation(libs.coroutines)
    implementation(libs.livedata)
    implementation(libs.viewmodel)
    implementation(libs.coroutines.android)
    implementation(libs.interceptor)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

}