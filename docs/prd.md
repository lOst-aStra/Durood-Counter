# DuroodCounter Product Requirements Document (PRD)

## Goals and Background Context

### Goals
- Deliver a minimal, distraction‑free Android app to aid Durood Shareef recitation with essential counting features.
- Enable immediate use: reach first increment within 1–2 seconds from app open.
- Ensure reliable session continuity with counts persisting across restarts until reset.
- Provide a beautiful, legible UI featuring Arabic Durood header and large, accessible controls.
- Support quick increments (+10, +33) and safe decrement (no negative totals), with clear reset.
- Offer offline‑first experience with no ads or tracking.

### Background Context
DuroodCounter addresses a focused need for users who regularly recite Durood Shareef and want an effortless way to track recitations. Many existing apps include ads or unnecessary complexity, introducing friction before users can begin. By prioritizing simplicity and a spiritually aligned design, DuroodCounter removes distractions and streamlines counting: a large primary +1 action, quick bulk adds (+10, +33), safe decrement, and an intuitive reset. 

The app persists state across app restarts to respect a user’s ongoing session. The UI emphasizes readability and calm aesthetics, with RTL support and a dedicated Arabic header, making it suitable for a wide range of users including elders who benefit from large tap targets and high contrast.

### Change Log
| Date       | Version | Description           | Author |
|------------|---------|-----------------------|--------|
|            |         |                       |        |

## Requirements

### Functional Requirements
- FR1: Increment count by +1 via a large, centered primary button.
- FR2: Decrement count by −1 with guard to prevent negative totals.
- FR3: Quick-add +10 with rollover into 100s sets as applicable.
- FR4: Quick-add +33 with rollover into 100s sets as applicable.
- FR5: Session reset with a clear 2-step confirmation to avoid accidental loss.
- FR6: Persist counts across app closes/restarts until explicit reset.
- FR7: Display Arabic Durood Shareef header with RTL support.
- FR8: Provide a minimal, beautiful UI aligned with the provided prototype.
- FR9: Offer optional haptic feedback on primary actions (respect device/user settings).
- FR10: Offline-first; no network required; no ads or analytics.
- FR11: Enable use within 1–2 seconds from app open to first increment.

### Non-Functional Requirements
- NFR1: Cold start time ≤ 1.5s on a mid-tier Android 12 device.
- NFR2: Crash-free sessions ≥ 99.5%.
- NFR3: Tap accuracy on +1 ≥ 98% on common devices.
- NFR4: Accessibility: large tap targets and high-contrast friendly color scheme.
- NFR5: Platform: Android phones, min OS Android 12 (API 31+).
- NFR6: Security/Privacy: No PII, no network access, compliant with Play policies.
- NFR7: Persistence reliability ≥ 99% across force-closes and restarts.
- NFR8: Smooth interaction at 60fps; efficient battery usage.
- NFR9: UI legibility for Arabic header and numeric counters across common devices.

## User Interface Design Goals

### Overall UX Vision
Minimal, distraction-free single-screen experience focused on dhikr. Immediate readiness: app opens directly to the counter with a large +1 primary action. Calm, spiritual aesthetic with Arabic Durood header (RTL) and clear, legible typography. Zero ads, zero distractions, and no onboarding blockers.

### Key Interaction Paradigms
- Single primary action (+1) as a large centered button.
- Quick-add actions: +10 and +33 with rollover to 100s sets.
- Safe decrement (−1) with guard against negative totals.
- Two-step confirmation for reset to prevent accidental loss.
- Persistent counts across restarts until explicit reset.
- Generous hit areas and high-contrast friendly colors.
- Optional haptic feedback that respects device/user settings.
- RTL-aware layout for the Arabic header; numerals remain consistently legible.
- No navigation flow; one main screen for MVP.

### Core Screens and Views
- Login Screen: Not applicable (MVP single-screen app).
- Main Dashboard: Single counter screen with Arabic header, counter card, +1 primary button, −1, +10, +33, reset with confirmation.
- Item Detail Page: Not applicable.
- Settings Page: Not required for MVP; consider later for haptic toggle or theme if needed.

### Accessibility
- Target practical alignment with WCAG AA for contrast and tap size.
- Large tap targets for all interactive elements.
- High-contrast friendly color options/themes.
- Clear feedback on taps/haptics.

### Branding
- Clean, calm palette aligned to prototype.
- Arabic header font: Noto Naskh Arabic for high-quality ligatures.
- Numeric counters: rounded Latin font (e.g., Nunito); prefer tabular/lining numerals (tnum) for stable alignment.
- Rounded cards and Material 3 elevation/shadows for visual clarity.

### Target Device and Platforms
- Android phones only.
- Android 12+ (API 31+).
- Offline-first; no network access required.

## Technical Assumptions

### Repository Structure
- Monorepo (single Android app module initially).
- Rationale: Single-screen MVP with no services; simplest to manage and scale later if needed.

### Service Architecture
- Monolith Android app; no backend or network dependencies.
- Rationale: Offline-first, no PII, avoids unnecessary complexity.

### Languages, Frameworks, Libraries
- Kotlin + Jetpack Compose (Material 3)
- State/Architecture: Single-Activity; MVVM with `ViewModel` and `State`
- Persistence: Jetpack DataStore (Preferences) for durable counts
- Haptics: `VibrationEffect` / `Vibrator` APIs (respect device/user settings)
- Fonts: Noto Naskh Arabic (header), Nunito (numeric); prefer tabular numerals (tnum)
- Icons: Material Icons; vector drawables

### Platforms and Versions
- Android phones only; Minimum OS Android 12 (API 31+)

### Testing Requirements
- Unit tests + basic UI happy-path checks.
- Rationale: Small scope MVP; target reliability first, expand as needed.

### Performance Targets
- Cold start ≤ 1.5s on mid-tier Android 12 device
- 60fps interaction; efficient battery usage

### Accessibility
- Large tap targets; high-contrast friendly palette
- Clear feedback on tap/haptic interactions
- RTL support for header; legible numerals across devices

### Security and Privacy
- No PII; no analytics or ads; no network access
- Compliant with Google Play policies

### Build & Release
- Gradle (KTS); latest stable AGP and Kotlin
- Signed APK/AAB for release

### Additional Assumptions and Requests
- No navigation for MVP (single screen)
- Optional haptics toggle may be deferred; initial behavior respects system settings
- Single developer; time-constrained—prioritize stability and UX polish
