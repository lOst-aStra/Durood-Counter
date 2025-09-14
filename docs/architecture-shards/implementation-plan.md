# 13) Implementation Plan (MVP)

1) Initialize Android project: Kotlin + Compose M3, minSdk 31.
2) Implement `CounterRepository` + `CounterLocalDataSource` with DataStore.
3) Build `CounterViewModel` with state and intents.
4) Compose UI: `DuroodScreen`, header, counter card, +1 primary, −1, +10, +33, reset confirm.
5) Wire haptics and small visual feedback.
6) Tests: unit for logic, UI tests for flows, persistence restore.
7) QA on two Android 12+ devices/emulators; verify cold start and persistence.
8) Prepare signed release build.
