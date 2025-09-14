# Build Tooling and Dependencies (Pinned, Latest Stable, Minimal)

## App Identity
- Application ID / Namespace: `com.lostastra.duroodcounter`

## Android SDK and Toolchain
- Compile SDK: 35 (Android 15)
- Target SDK: 35
- Min SDK: 31 (Android 12)
- Build Tools: 35.0.0
- Java Toolchain: 17
- Kotlin: 2.0.20

## Gradle and Android Gradle Plugin (AGP)
- Gradle Wrapper: 8.9
- Android Gradle Plugin (AGP): 8.6.0
- Repositories: `google()`, `mavenCentral()`

## Jetpack Compose and AndroidX
- Compose BOM: 2025.06.00 (use BOM-managed versions; do not hardcode artifact versions)
- UI: `androidx.compose.ui:ui`
- Material 3: `androidx.compose.material3:material3`
- Tooling Preview (debug): `androidx.compose.ui:ui-tooling-preview`
- Activity Compose: `androidx.activity:activity-compose:1.9.3`
- Lifecycle Runtime KTX: `androidx.lifecycle:lifecycle-runtime-ktx:2.8.4`
- Icons: `androidx.compose.material:material-icons-extended` (from BOM)
- Navigation: Omit for MVP (single screen)

## Data Persistence
- `androidx.datastore:datastore-preferences:1.1.1`

## Testing Stack
- Unit: `junit:junit:4.13.2`
- Instrumentation: `androidx.test.ext:junit:1.2.1`, `androidx.test.espresso:espresso-core:3.6.1`
- Compose UI Tests (androidTest/debug): `androidx.compose.ui:ui-test-junit4`, `androidx.compose.ui:ui-test-manifest`

## Build Configuration
- Plugins: `com.android.application`, `org.jetbrains.kotlin.android`
- Compile options: `sourceCompatibility=17`, `targetCompatibility=17`
- Kotlin: `jvmTarget = "17"`
- Compose: `buildFeatures { compose = true }`
- Compose Compiler Ext: align with AGP/Kotlin; if explicit needed, set to 1.6.20-compatible
- Packaging: exclude common META-INF license duplicates (standard AGP defaults OK)
- R8/ProGuard: `minifyEnabled=false` (debug), `true` (release) with default optimize rules + project keep rules if required
- Signing: debug auto; release signing config to be provided before publishing

## Assets
- App icon source: `prototype refrence/Durood-Counter-icon.png`
  - Note: During implementation, convert/provision launcher icons under `app/src/main/res/` (mipmap-anydpi-v26 XML and density variants) using Android Studio Asset Studio. No extra assets or libraries are required for MVP.

## Notes
- No internet permission; optional `VIBRATE` if stronger haptics are used.
- Fonts bundled as resources: Noto Naskh Arabic (header), Nunito (numeric; prefer tabular numerals).
- Keep dependency footprint minimal; add no unnecessary libraries.
