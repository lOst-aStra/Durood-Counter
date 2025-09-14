# 1) Overview

DuroodCounter is a single-activity, offline-first Android app that helps users track Durood Shareef recitations. The app emphasizes immediacy (time-to-first-count ≤ 2s), persistence across restarts, and a calm, legible UI. There is no backend.

- Paradigm: Single-module Android app (`app/`) using MVVM.
- UI: Jetpack Compose Material 3.
- State: `ViewModel` + immutable UI state.
- Persistence: Jetpack DataStore (Preferences).
- Haptics: `Vibrator`/`VibrationEffect` with settings-respectful behavior.
- Minimum OS: Android 12 (API 31+). Target and compile align to latest (per PRD).
