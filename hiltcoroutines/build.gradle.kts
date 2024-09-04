plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.custom.maven.publish)
}

android {
    namespace = "com.sean8.hiltcoroutines"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

mavenPublishing {
    coordinates("com.sean8", "hiltcoroutines", "0.0.7")

    pom {
        name.set("HiltCoroutines")
        description.set("A library for integrating Hilt with Coroutines.")
        inceptionYear.set("2023")
        url.set("https://github.com/seanzor/hiltcoroutines/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("Sean8")
                name.set("Sean Katz")
                url.set("https://sean8.com")
            }
        }
        scm {
            url.set("https://github.com/seanzor/hiltcoroutines/")
            connection.set("scm:git:git://github.com/seanzor/hiltcoroutines.git")
            developerConnection.set("scm:git:ssh://git@github.com/seanzor/hiltcoroutines.git")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.android)

    testImplementation(libs.junit)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.robolectric)
    testImplementation(libs.kotlinx.coroutines.test)

    ksp(libs.hilt.compiler)
}