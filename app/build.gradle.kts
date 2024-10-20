import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

}

android {
    namespace = "com.jihan.jetpack_instagram_clone"
    compileSdk = 34

    val file = rootProject.file("local.properties")
    val properties = Properties()
    properties.load(FileInputStream(file))


    defaultConfig {
        applicationId = "com.jihan.jetpack_instagram_clone"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "SECRET_NAME", properties.getProperty("secretName"))
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.koin.androidx.compose)

    // Coil
    implementation(libs.coil.compose)
    //implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.0-rc01")

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.gson)

    // implementation("com.google.accompanist:accompanist-permissions:0.36.0")

    implementation(libs.androidx.ui.text.google.fonts)

    // splash screen api
    implementation(libs.androidx.core.splashscreen)


    implementation(libs.iconsax.android)


    // voyager
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.tab.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.koin)


}

