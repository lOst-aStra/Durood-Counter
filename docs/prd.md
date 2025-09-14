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

## Epic List

- Epic 1: Foundation & Core Counter
  - Initialize Android app (Kotlin, Compose M3), single-activity MVVM.
  - Implement counter card with +1 primary action and persistence via DataStore.
  - Include Arabic header (RTL), basic theme, and haptic feedback.

- Epic 2: Bulk Increments and Decrement Safety
  - Add +10 and +33 actions with correct rollover into 100s sets.
  - Implement −1 with guard against negative totals.
  - Visual feedback and accessibility refinements (tap targets, contrast).

- Epic 3: Session Management and Reset
  - Two-step confirmation reset flow.
  - Robust persistence across restarts/force-closes; reliability validation via QA (no analytics).
  - Edge-case handling and UI polish.

- Epic 4: Performance and UX Polish
  - Optimize cold start to ≤ 1.5s on mid-tier Android 12 device.
  - 60fps interaction tuning; battery efficiency.
  - Typography finalization (Noto Naskh Arabic + Nunito with tnum), spacing, and touch feedback.

## Epic 1: Foundation & Core Counter

### Epic Goal
Establish a usable, reliable counter app foundation with +1 action, persistence, Arabic header, and basic theming/haptics.

### Story 1.1 Initialize Android app skeleton
As a developer,
I want a Kotlin + Compose M3 single-activity project scaffold,
so that I can implement the core counter UI quickly and cleanly.

#### Acceptance Criteria
1: Project uses Kotlin + Compose M3 with Single-Activity + MVVM setup
2: App builds and runs on Android 12+ device/emulator
3: Basic theme and typography applied

### Story 1.2 Counter UI with +1 action
As a user,
I want a large +1 button to increment the counter,
so that I can recite and track counts easily.

#### Acceptance Criteria
1: Large, centered +1 button increments visible counter
2: Counter is legible with rounded numeric font (e.g., Nunito; tabular numerals if available)
3: Arabic Durood header visible with RTL support

### Story 1.3 Persist counts with DataStore
As a user,
I want my count to persist across restarts,
so that I don’t lose progress until I reset intentionally.

#### Acceptance Criteria
1: Count restored on app launch after process death/restart
2: DataStore used for persistence with atomic writes
3: No network/analytics dependencies introduced

### Story 1.4 Haptic feedback and basic polish
As a user,
I want subtle haptic feedback on primary actions,
so that interactions feel responsive without being distracting.

#### Acceptance Criteria
1: Haptics on +1 tap (respect device/user settings)
2: Visual feedback on press
3: No regressions in performance targets for this epic

## Epic 2: Bulk Increments and Decrement Safety

### Epic Goal
Complete core counting workflows by adding bulk increments with correct rollover and safe decrement behavior, improving usability and accessibility.

### Story 2.1 Add +10 action with rollover
As a user,
I want a +10 button,
so that I can quickly add common sets aligned with my practice.

#### Acceptance Criteria
1: Tapping +10 increases the current count by 10
2: Correct rollover into 100s (e.g., 95 + 10 → 05 and increment completed-sets counter if applicable)
3: Visual feedback on tap; performance remains smooth (no dropped frames)

### Story 2.2 Add +33 action with rollover
As a user,
I want a +33 button,
so that I can follow common dhikr increments efficiently.

#### Acceptance Criteria
1: Tapping +33 increases the current count by 33
2: Correct rollover into 100s as above (carry logic mirrors +10)
3: Visual feedback on tap; respects haptics settings

### Story 2.3 Decrement with guard against negatives
As a user,
I want a −1 button that never produces a negative total,
so that I can safely correct mistakes without breaking the count.

#### Acceptance Criteria
1: Tapping −1 reduces the count by 1 but never below 0
2: If using a “completed sets” counter, decrement logic never produces invalid state
3: Clear UI state when at zero (e.g., disabled/soft state for −1)

### Story 2.4 Accessibility and interaction refinements
As a user,
I want large, comfortable tap targets and clear visual feedback,
so that the app is easy and pleasant to use across devices.

#### Acceptance Criteria
1: All interactive elements meet minimum touch target guidance
2: Contrast and feedback align with Accessibility goals
3: RTL header and numeric legibility remain intact

## Epic 3: Session Management and Reset

### Epic Goal
Provide intentional session control and resilience: reliable persistence across restarts/force-closes and a clear two-step reset flow, plus polish.

### Story 3.1 Two-step confirmation reset
As a user,
I want a clear two-step confirmation before resetting,
so that I don’t accidentally lose my session.

#### Acceptance Criteria
1: Reset flow requires explicit confirmation (e.g., dialog with clear messaging)
2: After confirmation, both current count (and completed-sets counter if present) reset to baseline
3: Visual state reflects reset immediately; focus returns to primary action

### Story 3.2 Robust persistence across lifecycle and force-close
As a user,
I want my counts to be safe across device restarts and app force-closes,
so that I don’t lose progress unexpectedly.

#### Acceptance Criteria
1: DataStore writes are atomic and consistent during rapid taps and app backgrounding
2: Verified restore after process death and force-close relaunch paths
3: No data corruption or stale state on resume

### Story 3.3 Reliability validation and edge-case handling
As a PM/QA,
I want validation against common edge cases,
so that reliability targets are met without analytics or tracking.

#### Acceptance Criteria
1: Define and verify a checklist for edge cases (e.g., rapid +1 spam, reset during write, low-memory kill)
2: In-app debug build logging behind a flag (no PII, no network)
3: Meets persistence reliability target (≥ 99% across force-closes/restarts) in test runs

### Story 3.4 UI polish and affordances
As a user,
I want consistent polish that reinforces clarity and intent,
so that the app feels trustworthy and calm.

#### Acceptance Criteria
1: Reset affordance is visible but not distracting; spacing and hierarchy refined
2: Clear empty/zero state visuals; no ambiguous states after reset
3: Micro-interactions align with performance and accessibility targets

## Epic 4: Performance and UX Polish

### Epic Goal
Meet performance targets and elevate UX polish to ensure a smooth, reliable, and delightful experience on Android 12+ devices.

### Story 4.1 Optimize cold start performance
As a user,
I want the app to open quickly,
so that I can start counting without delay.

#### Acceptance Criteria
1: Cold start ≤ 1.5s on a mid-tier Android 12 device
2: Measure with consistent methodology (e.g., Android Studio profiler) and document result
3: No functional regressions while achieving the target

### Story 4.2 60fps interaction and battery efficiency
As a user,
I want smooth interactions that feel responsive,
so that the app feels reliable and pleasant.

#### Acceptance Criteria
1: No visible jank during rapid taps and state updates
2: Avoid unnecessary recompositions; confirm stability with tooling
3: No battery-draining loops or excessive wake-ups observed in basic profiling

### Story 4.3 Typography and legibility refinement
As a user,
I want consistently legible text and numerals,
so that I can read and interact comfortably.

#### Acceptance Criteria
1: Arabic header uses Noto Naskh Arabic (verify ligature quality); RTL works throughout
2: Numeric counters use Nunito (or similar rounded Latin) with tabular numerals if available
3: Line-height, spacing, and contrast tuned for common device sizes

### Story 4.4 UI spacing, touch feedback, and micro-interactions
As a user,
I want clean spacing and clear feedback,
so that the interface feels calm and intentional.

#### Acceptance Criteria
1: Spacing/hierarchy refined (cards, paddings, margins)
2: Touch states/haptics are consistent and not distracting
3: Visual polish does not regress accessibility targets (tap size, contrast)

## Checklist Results Report

Before running the checklist and drafting the prompts, we can output the full updated PRD. Once confirmed, we will execute the PM checklist and populate the results below.

### PM Checklist Results
- [Pending] To be populated after running `.bmad-core/checklists/pm-checklist.md` via the checklist execution task.

## Next Steps

### UX Expert Prompt
Design a minimal, single-screen counter app for Android that helps users track Durood recitations. The app must include:

- A prominent Arabic header displaying the Durood Shareef text in a beautiful, legible Arabic font (Noto Naskh Arabic) with RTL support.
- A large, centered +1 button as the primary action for incrementing the count.
- Quick-add buttons for +10 and +33 with correct rollover into 100s sets.
- A -1 button that guards against negative totals (disabled at zero).
- A reset button with a two-step confirmation to prevent accidental loss.
- High legibility: use a rounded Latin font (Nunito) with tabular numerals for consistent alignment.
- Accessibility: large tap targets (minimum 48dp), high-contrast friendly color scheme, and clear visual feedback on interactions.
- Calm aesthetic: use a clean, distraction-free layout with a spiritual feel. Avoid any unnecessary elements.

The design must prioritize simplicity and speed, allowing users to start counting within 1-2 seconds of opening the app. Persistence of counts across app restarts is assumed (handled by the technical team). The UI should be offline-first with no ads or analytics.

### Architect Prompt
Using this PRD as input, create a concise architecture prompt for an Android 12+ Kotlin + Jetpack Compose single-activity app using MVVM and DataStore persistence. Ensure offline-first assumptions, performance targets (≤1.5s cold start, 60fps), and typography choices (Noto Naskh Arabic, Nunito with tabular numerals) are respected. Emphasize testability and reliability across lifecycle/force-close scenarios.
