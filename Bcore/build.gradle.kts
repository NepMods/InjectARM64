plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.vcore"
    compileSdk = (rootProject.ext["compileSdk"] as Int)


    defaultConfig {
        minSdk = (rootProject.ext["minSdk"] as Int)
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }

        ndk.apply{
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
        }
    }
    val cmake = rootProject.ext["cmakeVersion"] as String
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = cmake
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false  //not tested yet
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
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
    buildFeatures{
        aidl = true
        prefab = true
        viewBinding = true
    }

    packagingOptions.apply {
        excludes.add("**/libshadowhook.so")
    }
}

// it make update dependency update easy
val ktxversion = rootProject.ext["ktx_version"] as String
val stdlib_version = rootProject.ext["stdlib_version"] as String
val hiddenapibypass = rootProject.ext["hiddenapibypass"] as String
val xcrashversion = rootProject.ext["xcrashversion"] as String
val shadowhook = rootProject.ext["shadowhook"] as String
val googlematerial = rootProject.ext["googlematerial"] as String

dependencies {
    implementation("androidx.appcompat:appcompat") {
        exclude(group = "com.android.support", module = "support-compat")
    }
    implementation("androidx.core:core-ktx:$ktxversion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$stdlib_version")
    implementation ("com.iqiyi.xcrash:xcrash-android-lib:$xcrashversion")
    implementation("com.google.android.material:material:$googlematerial")
    implementation ("org.lsposed.hiddenapibypass:hiddenapibypass:$hiddenapibypass")
    implementation("com.bytedance.android:shadowhook:$shadowhook")
    implementation ("top.canyie.pine:core:0.2.8")
    implementation ("top.canyie.pine:xposed:0.0.9")
}