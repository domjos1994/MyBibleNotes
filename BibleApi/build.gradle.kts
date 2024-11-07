import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.serialization.core)
    alias(libs.plugins.kapt.library)
    alias(libs.plugins.hilt.library)
}

android {
    namespace = "de.dojodev.mybiblenotes.bibleapi"
    compileSdk = rootProject.extra["sdk_compile"] as Int

    defaultConfig {
        minSdk = rootProject.extra["sdk_min"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        // load api key
        val keystoreFile = project.rootProject.file("BibleAPI/api.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())
        val apiKey = properties.getProperty("key") ?: ""

        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = apiKey
        )
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
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // okhttp
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // serialization
    implementation(libs.serialization.json)

    // hilt
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)

    // testing dependencies
    testImplementation(libs.junit)
    testImplementation(libs.okhttp.mock)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}