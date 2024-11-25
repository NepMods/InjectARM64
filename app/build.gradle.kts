plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dev.rikka.tools.materialthemebuilder")
}

android {
    namespace = "com.reveny.virtualinject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.reveny.virtualinject"
        minSdk = 26
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
        buildConfig = true
        viewBinding = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        aidl = true
        prefab = true
        viewBinding = true
        buildConfig = true
    }


    externalNativeBuild {
        ndkBuild {
            path("src/main/jni/Android.mk")
        }
    }
    ndkVersion = (rootProject.ext["ndkVersion"] as String)
}

materialThemeBuilder {
    themes {
        for ((name, color) in listOf(
            "Sakura" to "FF9CA8",
        )) {
            create("Material$name") {
                lightThemeFormat = "ThemeOverlay.Light.%s"
                darkThemeFormat = "ThemeOverlay.Dark.%s"
                primaryColor = "#$color"
            }
        }
    }
    generatePalette = true
}

dependencies {
    implementation(project(":Bcore"))

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.rikkax)
}

configurations.all {
    exclude("org.jetbrains", "annotations")
    exclude("androidx.appcompat", "appcompat")
}