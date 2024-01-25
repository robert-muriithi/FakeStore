plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id ("com.google.devtools.ksp") version "1.9.20-1.0.14"
    kotlin("plugin.serialization") version "1.9.0"
    id("com.google.dagger.hilt.android")



}

apply {
    from("$rootDir/core-dependecies.gradle")
}
apply {
    from("$rootDir/test-dependecies.gradle")
}

apply {
    from("$rootDir/compose-dependencies.gradle")
}

android {
    namespace = "dev.robert.cart"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    kotlin {
        jvmToolchain(11)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {
    //Compose destinations
    implementation("io.github.raamcosta.compose-destinations:animations-core:1.9.55")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.55")

    implementation(project(":network"))
    implementation(project(":database"))

}