plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    `maven-publish`
}

android {
    namespace = "com.github.seanzor.hiltcoroutines"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            // Add the AAR file manually
            artifact(layout.buildDirectory.file("outputs/aar/${project.name}-release.aar"))

            groupId = "com.github.seanzor"
            artifactId = "hiltcoroutines"
            version = "0.0.4"

//            artifact(tasks["sourcesJar"])
//            artifact(tasks["javadocJar"])

            pom {
                name.set("HiltCoroutines")
                description.set("A library for integrating Hilt with Coroutines.")
                url.set("https://github.com/seanzor/hiltcoroutines")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("seanzor")
                        name.set("Sean")
                        email.set("connect@sean8.com")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/seanzor/hiltcoroutines.git")
                    developerConnection.set("scm:git:ssh://github.com/seanzor/hiltcoroutines.git")
                    url.set("https://github.com/seanzor/hiltcoroutines")
                }
            }
        }
    }
}

tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets["main"].java.srcDirs)
    }

    val javadoc by creating(Javadoc::class) {
        source = fileTree(android.sourceSets["main"].java.srcDirs)
    }

    val javadocJar by creating(Jar::class) {
        archiveClassifier.set("javadoc")
        from(javadoc.destinationDir)
    }

    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
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