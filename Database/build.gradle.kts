plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt.library)
    alias(libs.plugins.hilt.library)
}

android {
    namespace = "de.dojodev.mybiblenotes.database"
    compileSdk = rootProject.extra["sdk_compile"] as Int

    defaultConfig {
        minSdk = rootProject.extra["sdk_min"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
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

    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.pagination)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)

    // hilt
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)

    // testing dependencies
    testImplementation(libs.junit)
    testImplementation(libs.room.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}