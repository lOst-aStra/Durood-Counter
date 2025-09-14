# Technical Assumptions

## Repository Structure
- Monorepo (single Android app module initially).
- Rationale: Single-screen MVP with no services; simplest to manage and scale later if needed.

## Service Architecture
- Monolith Android app; no backend or network dependencies.
- Rationale: Offline-first, no PII, avoids unnecessary complexity.

## Languages, Frameworks, Libraries
- Kotlin + Jetpack Compose (Material 3)
- State/Architecture: Single-Activity; MVVM with `ViewModel` and `State`
- Persistence: Jetpack DataStore (Preferences) for durable counts
- Haptics: `VibrationEffect` / `Vibrator` APIs (respect device/user settings)
- Fonts: Noto Naskh Arabic (header), Nunito (numeric); prefer tabular numerals (tnum)
- Icons: Material Icons; vector drawables

## Platforms and Versions
- Android phones only; Minimum OS Android 12 (API 31+)

## Testing Requirements
- Unit tests + basic UI happy-path checks.
- Rationale: Small scope MVP; target reliability first, expand as needed.

## Performance Targets
- Cold start ≤ 1.5s on mid-tier Android 12 device
- 60fps interaction; efficient battery usage

## Accessibility
- Large tap targets; high-contrast friendly palette
- Clear feedback on tap/haptic interactions
- RTL support for header; legible numerals across devices

## Security and Privacy
- No PII; no analytics or ads; no network access
- Compliant with Google Play policies

## Build & Release
- Gradle (KTS); latest stable AGP and Kotlin
- Signed APK/AAB for release

## Additional Assumptions and Requests
- No navigation for MVP (single screen)
- Optional haptics toggle may be deferred; initial behavior respects system settings
- Single developer; time-constrained—prioritize stability and UX polish
