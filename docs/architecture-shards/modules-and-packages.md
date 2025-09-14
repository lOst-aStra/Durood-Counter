# 4) Module & Package Structure

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
