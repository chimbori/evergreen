import org.gradle.api.JavaVersion.VERSION_17

// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

plugins {
  id("com.android.application")
  id("kotlin-android")
  kotlin("kapt")
  alias(libs.plugins.play.publisher)
}

val versionMajor = 1
val versionMinor = 0
val versionPatch = 0

android {
  namespace = "app.evergreen"
  compileSdk = 33

  buildFeatures {
    buildConfig = true
  }
  defaultConfig {
    applicationId = "app.evergreen"
    minSdk = 21
    targetSdk = 33
    versionCode = versionMajor * 10000 + versionMinor * 100 + versionPatch
    versionName = "${versionMajor}.${versionMinor}.${versionPatch}"
  }

  signingConfigs {
    register("appSigningKey") {
      enableV2Signing = true
      storeFile = file("../signing-keys.keystore")
      storePassword = System.getenv("KEYSTORE_PASSWORD")
      keyAlias = "evergreen"
      keyPassword = System.getenv("KEYSTORE_PASSWORD")
    }
  }

  buildTypes {
    named("release") {
      isMinifyEnabled = true
      isShrinkResources = true
      isCrunchPngs = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "evergreen.pro", "moshi.pro", "kotlin.pro", "moshi-kotlin.pro"
      )
      resValue("string", "app_version", "${defaultConfig.versionName}")
      signingConfig = signingConfigs.getByName("appSigningKey")
    }
  }

  kotlinOptions {
    jvmTarget = "17"
  }
  compileOptions {
    sourceCompatibility = VERSION_17
    targetCompatibility = VERSION_17
  }
}

dependencies {
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.leanback)
  implementation(libs.androidx.workmanager.runtime)
  implementation(libs.coil)
  implementation(libs.coroutines)
  implementation(libs.kotlin.stdlib)
  implementation(libs.moshi)
  implementation(libs.moshi.adapters)
  implementation(libs.okhttp)
  kapt(libs.moshi.kotlin.codegen)
}

play {
  serviceAccountCredentials.set(file("../service-account-keys.json"))
  track.set("alpha")  // GitHub Actions automatically pushes to Alpha, so that’s our starting point.
  fromTrack.set("alpha")
  promoteTrack.set("beta")  // By default, promote to Beta
}
