// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.serialization.core) apply false
}

buildscript {
    extra.apply {
        set("java_version", "17")
        set("java_jvm", JavaVersion.VERSION_17)
        set("sdk_compile", 35)
        set("sdk_min", 26)
        set("compose_version", "1.5.8")
        set("version", 1)
        set("version_name", "1.0.Alpha.1")
        set("minify", false)
    }
}