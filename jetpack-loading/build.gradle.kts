import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)

    id("maven-publish")
    id("signing")
}

kotlin {
    val webConfig : KotlinJsTargetDsl.() -> Unit = {
        moduleName = "jetpack-loading"
        browser {
            commonWebpackConfig {
                outputFileName = "jetpack-loading.js"
            }
        }

        nodejs()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs(webConfig)

    js(IR,webConfig)

    jvm("desktop")

    androidTarget {
        publishLibraryVariants("release", "debug")

        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "jetpack-loading"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
        }
    }
}

android {
    namespace = "com.spr.jetpack_loading"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


// Based on https://medium.com/kodein-koders/publish-a-kotlin-multiplatform-library-on-maven-central-6e8a394b7030
publishing {
    publications {
        repositories {
            maven {
                name="oss"

                val repositoryId = System.getenv("SONATYPE_REPOSITORY_ID")
                val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deployByRepositoryId/$repositoryId/")
                val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

                credentials {
                    username = System.getenv("SONATYPE_USERNAME")
                    password = System.getenv("SONATYPE_PASSWORD")
                }
            }

            mavenLocal()
        }

        withType<MavenPublication> {
            pom {
                groupId = "com.github.MahboubehSeyedpour"
                version = "0.1.1.2-SNAPSHORT"

                name.set("jetpack-loading")
                description.set("Elegant Android loading indicator using Jetpack Compose")

                licenses {
                    name.set("Apache License Version 2.0")
                }

                url.set("https://github.com/MahboubehSeyedpour/jetpack-loading")

                issueManagement {
                    system.set("Github")
                    url.set("https://github.com/MahboubehSeyedpour/jetpack-loading/issues")
                }

                scm {
                    connection.set("https://github.com/MahboubehSeyedpour/jetpack-loading.git")
                    url.set("https://github.com/MahboubehSeyedpour/jetpack-loading")
                }
            }
        }
    }
}

// TODO : Enable this again when publishing to mavenCentral
/*
signing {
    useInMemoryPgpKeys(
        System.getenv("GPG_PRIVATE_KEY"),
        System.getenv("GPG_PRIVATE_PASSWORD")
    )

    sign(publishing.publications)
}*/