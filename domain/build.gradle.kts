plugins {
    id("kotlin")
}

dependencies {
    testImplementation(Dependencies.Test.JUNIT)
    implementation("javax.inject:javax.inject:1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
}