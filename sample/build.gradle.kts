import dev.nucleusframework.desktop.application.dsl.CompressionLevel
import dev.nucleusframework.desktop.application.dsl.NativeImageOptimization
import dev.nucleusframework.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

// Release builds are tag-driven: the CI exports RELEASE_VERSION=<tag>.
val releaseVersion =
    System
        .getenv("RELEASE_VERSION")
        ?.removePrefix("v")
        ?.takeIf { it.isNotBlank() && it.first().isDigit() }
        ?: "1.0.0"

// Native installers only accept numeric versions: drop any pre-release suffix.
val nativePackageVersion = releaseVersion.substringBefore("-")

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.ksp)
    alias(libs.plugins.nucleus)
}

kotlin {
    @Suppress("DEPRECATION")
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(project(":macosui"))
            implementation(project(":macosui-icons-extended"))
            implementation(project(":gallery-annotations"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.compose.nav3)
            implementation(libs.androidx.lifecycle.viewmodel.nav3)
            implementation(libs.kotlinx.datetime)
            implementation(libs.highlights)
            implementation(libs.icons.lucide.cmp)
            implementation(libs.kotlin.math)
            implementation(project(":macos-markdown"))
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation(libs.kotlinx.datetime)
            implementation(libs.nucleus.core.runtime)
            implementation(libs.nucleus.darkmode.detector)
            implementation(libs.nucleus.system.color)
            // Ships the L1 GraalVM reachability metadata, the font
            // substitutions and the META-INF/services globs the native image
            // needs (without it: "platform encoding not initialized").
            implementation(libs.nucleus.graalvm.runtime)
        }
        webMain.dependencies {
            implementation(libs.navigation3.browser)
        }
    }
}

// KSP for commonMain — generates once, shared by all targets
dependencies {
    debugImplementation(libs.compose.uiTooling)
    add("kspCommonMainMetadata", project(":gallery-ksp"))
}

// Make generated sources visible to commonMain
kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}

// Ensure KSP runs before compilation
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

android {
    namespace = "dev.nucleusframework.macoscompose.sample"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "dev.nucleusframework.macoscompose.sample"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}


nucleus.application {
    mainClass = "dev.nucleusframework.macoscompose.sample.MainKt"

    nativeDistributions {
        targetFormats(TargetFormat.Dmg, TargetFormat.Nsis, TargetFormat.Deb)
        compressionLevel = CompressionLevel.Ultra
        packageName = "macosui-gallery"
        cleanupNativeLibs = true
        packageVersion = nativePackageVersion
        linux {
            debMaintainer = "Nucleus"
            homepage = "https://nucleusframework.dev"
        }
    }

    graalvm {
        isEnabled = true
        javaLanguageVersion = 25
        imageName = "macosui-sample"
        optimization = NativeImageOptimization.SIZE
    }
}
