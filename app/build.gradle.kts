plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") // Keep it when use room
    id("kotlin-parcelize") // Keep it when passing data using parcelize
}

android {
    namespace = "yprsty.android.playground"
    compileSdk = 34

    defaultConfig {
        applicationId = "yprsty.android.playground"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    // Default dependencies
    val coreKtxVersion = "1.12.0"
    val appCompatVersion = "1.6.1"
    val materialVersion = "1.11.0"
    val constraintLayoutVersion = "2.1.4"

    // Jetpack dependencies
    val lifecycleVersion = "2.7.0"
    val pagingVersion = "3.2.1"
    val viewPagerVersion = "1.0.0"
    val roomVersion = "2.6.1"
    val dataStoreVersion = "1.0.0"
    val navVersion = "2.7.6"

    // Third-party dependencies
    val retrofitVersion = "2.9.0"
    val okhttp3VersionBom = "4.12.0"
    val coilVersion = "2.5.0"
    val koinAndroidVersion = "3.5.3"
    val coroutineVersion = "1.7.3"
    val rxBindingVersion = "4.0.0"
    val timberVersion = "5.0.1"

    // Testing dependencies
    val junitVersion = "4.13.2"
    val espressoIdlingResourceVersion = "3.5.1"
    val coroutineTestVersion = "1.7.3"
    val coreTestingVersion = "2.2.0"
    val mockitoCoreVersion = "5.8.0"
    val mockitoInlineVersion = "4.11.0"
    val runnerTestVersion = "1.5.2"
    val rulesTestVersion = "1.5.0"
    val extVersion = "1.1.5"
    val truthVersion = "1.5.0"
    val espressoVersion = "3.5.1"

    // Default
    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")

    // View model
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    // Live data
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // Paging3
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")
    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:$viewPagerVersion")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion") // Use Kotlin Symbol Processing
    implementation("androidx.room:room-ktx:$roomVersion") // Support coroutines
    implementation("androidx.room:room-paging:$roomVersion") // Paging 3 integration

    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:$dataStoreVersion")

    // Navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    // Navigation component support feature module
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    // Gson Converter
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    // Okhttp3 with BOM
    implementation(platform("com.squareup.okhttp3:okhttp-bom:$okhttp3VersionBom"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Coil
    implementation("io.coil-kt:coil:$coilVersion")

    // Koin
    implementation("io.insert-koin:koin-android:$koinAndroidVersion")

    // Coroutine (core & android)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

    // RxBinding
    implementation("com.jakewharton.rxbinding4:rxbinding:$rxBindingVersion")

    // Timber Logs
    implementation("com.jakewharton.timber:timber:$timberVersion")

    // Can be either "implementation" or "androidTestImplementation"
    // Validate asynchronous operations when UI test
    implementation("androidx.test.espresso:espresso-idling-resource:$espressoIdlingResourceVersion")

    // LOCAL TEST
    // JUnit 4 framework
    testImplementation("junit:junit:$junitVersion")
    // Coroutine Test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineTestVersion")
    // InstantTaskExecutorRule - Test Helper for Live Data (Change how background work to synchronous)
    testImplementation("androidx.arch.core:core-testing:$coreTestingVersion")
    // Mockito framework
    testImplementation("org.mockito:mockito-core:$mockitoCoreVersion")
    // Mockito-inline for compatibility and support for Kotlin features
    testImplementation("org.mockito:mockito-inline:$mockitoInlineVersion")

    // Testing dependencies available for local test:
    // https://developer.android.com/training/testing/local-tests#dependencies

    // INSTRUMENTED TEST
    // AndroidJUnitRunner
    // Responsible execute Android instrumented test on device or emulator
    // Provides a set of APIs and mechanisms for running, handling lifecycle events, and generating test results
    androidTestImplementation("androidx.test:runner:$runnerTestVersion")
    // JUnit Rules
    // Customize behavior of like common scenarios before or after test execution
    // Used to setting up and tearing down test fixtures, mocking, and more.
    androidTestImplementation("androidx.test:rules:$rulesTestVersion")
    // Assertions
    androidTestImplementation("androidx.test.ext:junit:$extVersion")
    androidTestImplementation("androidx.test.ext:truth:$truthVersion")
    // UI testing with Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso:espresso-contrib:$espressoVersion")
    androidTestImplementation("androidx.test.espresso:espresso-intents:$espressoVersion")

    // Most common AndroidX Test dependencies:
    // https://developer.android.com/training/testing/instrumented-tests/androidx-test-libraries/test-setup#add-libraries
}