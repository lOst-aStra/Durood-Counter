# DuroodCounter Architecture (Android 12+, Kotlin + Jetpack Compose)

> This architecture has been sharded for focused review. See `docs/architecture-shards/README.md` for the index.

## 1) Overview
DuroodCounter is a single-activity, offline-first Android app that helps users track Durood Shareef recitations. The app emphasizes immediacy (time-to-first-count ≤ 2s), persistence across restarts, and a calm, legible UI. There is no backend.

- Paradigm: Single-module Android app (`app/`) using MVVM.
- UI: Jetpack Compose Material 3.
- State: `ViewModel` + immutable UI state.
- Persistence: Jetpack DataStore (Preferences).
- Haptics: `Vibrator`/`VibrationEffect` with settings-respectful behavior.
- Minimum OS: Android 12 (API 31+). Target and compile align to latest (per PRD).

## 2) Scope & Constraints
- Offline-first. No network, analytics, or ads.
- Single screen (no navigation needed for MVP).
- Reliability and simplicity over features.
- Accessibility: large tap targets (≥48dp), high-contrast friendly palette.

## 3) High-Level Architecture
```
+-----------------------------+
|         UI Layer            |  Jetpack Compose M3
|  - DuroodScreen()           |  - Arabic header (RTL)
|  - CounterCard()            |  - Primary +1, −1, +10, +33, Reset
+--------------▲--------------+
               | State (immutable)
+--------------▼--------------+
|       ViewModel (MVVM)      |
|  - CounterViewModel         |
|  - Exposes StateFlow<State> |
|  - Business logic:          |
|    increment1/add10/add33   |
|    decrement/reset with 2-step
+--------------▲--------------+
               | Repository API
+--------------▼--------------+
|        Data Layer            |
|  - CounterRepository        |
|  - DataStore<Preferences>   |
|  - Atomic read/update       |
+-----------------------------+
```

## 4) Module & Package Structure
Single Android app module `app/` with the following packages (Kotlin):

- `com.lostastra.duroodcounter.ui`
  - `DuroodScreen.kt` – Screen scaffold & layout
  - `components/` – `HeaderArabic.kt`, `CounterCard.kt`, `PrimaryButton.kt`, `ActionsRow.kt`, `ResetConfirmDialog.kt`
  - `theme/` – `Color.kt`, `Type.kt` (fonts), `Theme.kt`
- `com.lostastra.duroodcounter.presentation`
  - `CounterViewModel.kt` – UI state, intents, side-effects (haptics trigger)
  - `CounterState.kt` – immutable state data class
- `com.lostastra.duroodcounter.data`
  - `CounterRepository.kt` – Public data contract
  - `datastore/CounterLocalDataSource.kt` – DataStore impl, atomic ops
- `com.lostastra.duroodcounter.core`
  - `Haptics.kt` – Utilities to respect system settings
  - `Result.kt` (optional) – light wrapper if needed later
- `com.lostastra.duroodcounter.di`
  - `AppModule.kt` – Provides repo, DataStore, and viewModel instances (plain manual wiring for MVP)

Notes:
- Keep DI simple (manual) to minimize footprint. Hilt can be added later if needed.
- No navigation package required for MVP.

## 5) Data Model & Persistence
Data is small, numeric, and session-oriented. Use DataStore (Preferences) for durability and atomic updates.

Preferences schema (keys):
- `current_count: Int` – 0..99 (rolling set window)
- `completed_sets: Int` – number of full 100s completed
- `last_updated_epoch_ms: Long` – optional; for future audit/UX

DataStore specifics:
- File name: `counter_prefs.pb` (though using Preferences, not Proto; name is arbitrary)
- Reads: single flow returning a small state DTO for repository mapping.
- Writes: `updateData { prefs -> ... }` ensuring atomicity.
- Threading: Default DataStore dispatcher; repository exposes suspend functions + cold flows.

## 6) Business Logic (Rollover, Guards)
Rollover is defined on a base of 100.

- `increment1()`:
  - `current = (current + 1) % 100`
  - `if (current == 0) completed_sets += 1`
- `add10()` / `add33()`:
  - `sum = current + delta`
  - `completed_sets += sum / 100`
  - `current = sum % 100`
- `decrement()`:
  - If total (completed_sets*100 + current) == 0 → no-op
  - Else if current > 0 → `current -= 1`
  - Else (current == 0 and completed_sets > 0) → `completed_sets -= 1; current = 99`
- `reset()`:
  - Requires a two-step confirmation.
  - `current = 0; completed_sets = 0`

All mutations go through the repository with DataStore atomic updates.

## 7) UI Composition (Compose M3)
Core composables and responsibilities:
- `DuroodScreen()` – top-level screen with Scaffold, padding, and content area
- `HeaderArabic()` – shows Arabic Durood Shareef text; uses Noto Naskh Arabic; RTL-aware
- `CounterCard()` – prominent numeric display using Nunito with tabular numerals
- `PrimaryButton()` – large +1 action (dominant visual weight)
- `ActionsRow()` – −1, +10, +33 horizontally with generous spacing and 48dp+ targets
- `ResetConfirmDialog()` – two-step confirmation before reset

Typography & Fonts:
- Bundle fonts in `app/src/main/res/font/`:
  - `noto_naskh_arabic_regular.ttf` → Arabic header `FontFamily`
  - `nunito_variable.ttf` (or fixed weights) with tabular numeral feature if available
- Define `Type.kt` to wire these families into `MaterialTheme(typography)`.

Colors & Layout:
- Calm, distraction-free scheme aligned with prototype; ensure high contrast.
- Rounded cards and clear elevation for `CounterCard`.

Haptics:
- Trigger subtle haptic feedback on primary actions where device/user settings permit.
- Centralize in `core/Haptics.kt` and call from ViewModel via one-shot UI event or callback.

RTL & Localization:
- `AndroidManifest.xml` → `android:supportsRtl="true"`.
- Header composable uses `LayoutDirection.Rtl` for Arabic content if needed.

## 8) ViewModel & State
`CounterViewModel` exposes:
- `state: StateFlow<CounterState>` with fields:
  - `current: Int`
  - `completedSets: Int`
  - `isResetConfirmVisible: Boolean`
  - `hapticsAvailable: Boolean` (optional, cached from system)

Intents:
- `onIncrement()`, `onDecrement()`, `onAdd10()`, `onAdd33()`
- `onRequestReset()`, `onConfirmReset()`, `onDismissReset()`

Lifecycle:
- Collect repository flow in init and map to `CounterState`.
- Use `viewModelScope` for suspend operations.

## 9) Performance Considerations
- Cold start ≤ 1.5s:
  - Avoid heavy work in `Application` or `Activity#onCreate`.
  - Lazy-load fonts and avoid excessive recomposition.
  - Keep dependency graph minimal; no reflection-heavy DI.
- Rendering @ 60fps:
  - Keep state minimal; avoid large recompositions by scoping state.
  - Use `remember` and stable data classes; avoid lambdas capturing mutable vars.
- DataStore:
  - Small payload; atomic updates; avoid write amplification (batch operations where feasible).
- Optional later: Baseline Profiles to improve cold start on release builds.

## 10) Testing Strategy
Unit Tests (JVM):
- Rollover math: `increment1`, `add10`, `add33`, `decrement` with edge cases.
- Reset flow: ensure state becomes baseline after confirmation.

Instrumentation/UI Tests (androidTest):
- Compose UI tests for primary flows:
  - +1 increments display.
  - −1 guard at zero.
  - +10/+33 rollover into 100s sets.
  - Reset two-step confirmation.
- Persistence restore: write via repo, relaunch activity, assert state.

Test Utilities:
- Provide a fake in-memory repository or temporary DataStore for reliable tests.
- Use test tags for key nodes (e.g., `testTag("counter-value")`).

## 11) Build & Dependencies (Aligned with PRD)
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

## 12) Release & Signing
- Debug: auto.
- Release: standard signing config; produce both APK and AAB as needed.
- No Internet permission by default; optional `VIBRATE` if using stronger haptics.
- App icon from `prototype refrence/Durood-Counter-icon.png` → convert via Asset Studio to mipmap variants.

## 13) Implementation Plan (MVP)
1) Initialize Android project: Kotlin + Compose M3, minSdk 31.
2) Implement `CounterRepository` + `CounterLocalDataSource` with DataStore.
3) Build `CounterViewModel` with state and intents.
4) Compose UI: `DuroodScreen`, header, counter card, +1 primary, −1, +10, +33, reset confirm.
5) Wire haptics and small visual feedback.
6) Tests: unit for logic, UI tests for flows, persistence restore.
7) QA on two Android 12+ devices/emulators; verify cold start and persistence.
8) Prepare signed release build.

## 14) Future Extensions (Out of Scope for MVP)
- Settings screen for haptics toggle/theme.
- Localization strings split and selectable language.
- Advanced analytics (if ever desired) – currently intentionally omitted.

## 15) PRD Alignment
- Functional: FR1–FR11 covered via UI actions, DataStore persistence, and offline-first design.
- Non-Functional: NFR1–NFR9 addressed via performance, accessibility, and testing guidance above.

---
This document defines a lightweight, reliable architecture that can be implemented quickly while meeting the UX and reliability goals. It avoids unnecessary complexity and aligns with the MVP constraints.
