# 11) Build & Dependencies (Aligned with PRD)

- Application ID / Namespace: `com.lostastra.duroodcounter`
- Java Toolchain: 17
- Kotlin: 2.0.20
- Compile/Target SDK: 35
- Min SDK: 31
- Gradle Wrapper: 8.9
- AGP: 8.6.0
- Repositories: `google()`, `mavenCentral()`

Dependencies:
- Compose BOM: 2025.06.00
  - `androidx.compose.ui:ui`
  - `androidx.compose.material3:material3`
  - `androidx.compose.material:material-icons-extended`
  - `androidx.compose.ui:ui-tooling-preview` (debug)
- Activity Compose: `androidx.activity:activity-compose:1.9.3`
- Lifecycle: `androidx.lifecycle:lifecycle-runtime-ktx:2.8.4`
- DataStore: `androidx.datastore:datastore-preferences:1.1.1`
- Testing:
  - Unit: `junit:junit:4.13.2`
  - Instrumentation: `androidx.test.ext:junit:1.2.1`, `androidx.test.espresso:espresso-core:3.6.1`
  - Compose UI tests: `androidx.compose.ui:ui-test-junit4`, `androidx.compose.ui:ui-test-manifest`

Build Features:
- `buildFeatures { compose = true }`
- Kotlin options: `jvmTarget = "17"`
- Packaging: default excludes OK; enable R8 minification only for release with default rules.
