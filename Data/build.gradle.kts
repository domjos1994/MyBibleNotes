plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt.library)
    alias(libs.plugins.hilt.library)
}

android {
    namespace = "de.dojodev.mybiblenotes.data"
    compileSdk = rootProject.extra["sdk_compile"] as Int

    defaultConfig {
        minSdk = rootProject.extra["sdk_min"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = rootProject.extra["minify"] as Boolean
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = rootProject.extra["java_jvm"] as JavaVersion
        targetCompatibility = rootProject.extra["java_jvm"] as JavaVersion
    }
    kotlinOptions {
        jvmTarget = rootProject.extra["java_version"] as String
    }
}

dependencies {
    // projects
    implementation(project(":BibleApi"))
    implementation(project(":Database"))

    // hilt
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)

    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}