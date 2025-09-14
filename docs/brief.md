# Project Brief: DuroodCounter

## Introduction
This brief documents the scope and direction for DuroodCounter, a minimal, distraction‑free Android application to aid recitation of Durood Shareef with a built‑in counter. Mode: Interactive creation, distilled into a finalized brief.

## Executive Summary
- Product Concept: DuroodCounter is a mobile application designed to facilitate the recitation of Durood Shareef. It offers a simple and minimalistic interface to start reciting immediately, with features to increment, decrement, add 10, add 33, and reset the session. The app retains the count even when closed, until reset.
- Primary Problem Being Solved: Provides a straightforward solution for users to keep track of their Durood Shareef recitations without the complexity of additional features or advertisements.
- Target Market: Individuals who regularly recite Durood Shareef and seek a convenient way to track their recitations using a mobile app.
- Key Value Proposition: Offers a beautiful, user‑friendly interface with essential counting features, ensuring ease of use and a focus on the spiritual practice without distractions.

## Problem Statement
- Current State and Pain Points: Many individuals who recite Durood Shareef regularly face the challenge of keeping an accurate count of their recitations. Existing solutions may be overly complex or lack the simplicity needed for a focused spiritual practice.
- Impact of the Problem: Without a simple and reliable counting tool, users may lose track of their recitations, which can be discouraging and disrupt their spiritual routine.
- Why Existing Solutions Fall Short: Current apps may include unnecessary features, advertisements, or require multiple steps before starting the recitation, which detracts from the primary goal of uninterrupted spiritual practice.
- Urgency and Importance: Providing a dedicated tool that focuses solely on counting Durood Shareef recitations can enhance the spiritual experience for users, making it a valuable addition to their routine.

## Proposed Solution
- Core Concept and Approach: Develop an Android APK that provides a straightforward and intuitive interface for counting Durood Shareef recitations. The app will feature increment, decrement, add 10, add 33, and session reset buttons, with a beautiful UI displaying Arabic Durood Shareef for recitation.
- Key Differentiators: Unlike other apps that may include ads or unnecessary features, DuroodCounter focuses solely on the spiritual practice, ensuring a distraction‑free experience with essential counting features.
- Why This Solution Will Succeed: By prioritizing simplicity and user experience, DuroodCounter addresses the core needs of its target users, offering a reliable tool for their spiritual practice.
- High‑Level Vision: A seamless, focused, and aesthetically pleasing experience for Durood Shareef recitation.

## Target Users
- Primary User Segment: Practicing Muslims who regularly perform dhikr and recite Durood Shareef (ages 18–45, smartphone‑comfortable). They want to start counting instantly, need reliability/persistence, and value a spiritually aligned UI.
- Secondary User Segment: Elders and beginners (45+) who prefer large tap targets and high contrast, minimal interactions, and persistent counts until reset.
- Optional Segment: Students/attendees of khanqah/tariqah who appreciate bulk increments (+10, +33) aligned with common sets.

## Goals & Success Metrics
- Business Objectives:
  - Launch a stable Android APK (min Android 12) that starts counting within 1 tap.
  - Achieve consistent session continuity (counts persist across app restarts until reset).
  - Deliver a polished, distraction‑free UI aligned with the prototype aesthetic.
- User Success Metrics:
  - Time‑to‑first‑count: ≤ 2s from app open to first increment.
  - Error recovery: Users can easily undo with −1 without confusion.
  - Readability: Arabic header and buttons remain legible on small devices.
- KPIs:
  - App open → first tap conversion ≥ 90%.
  - Session persistence reliability ≥ 99% across force‑closes and restarts.
  - Crash‑free sessions ≥ 99.5%.
  - Tap accuracy on +1 ≥ 98% on common devices.
  - Cold start time ≤ 1.5s on a mid‑tier Android 12 device.

## MVP Scope (Approved)
- Increment +1 primary action with large, centered button
- Decrement −1 with guard against going below 0 total
- Quick‑add buttons: +10 and +33 with rollover into 100s sets
- Rolling set logic: 00–99; at 100, increment “Total Tasbeeh Completed”
- Session reset with 2‑step confirmation
- Persistent storage of counts across app closes/restarts until reset
- Arabic Durood Shareef header (RTL), visually aligned to prototype
- Minimal, beautiful UI (colors/layout/rounded cards as in prototype)
- Haptic feedback on primary actions (when supported)
- Offline‑first; no network required
- Minimum Android version: Android 12

## Technical Considerations
- Platform Requirements:
  - Target Platforms: Android phones
  - Minimum OS: Android 12 (API 31+)
  - Performance: Cold start ≤ 1.5s; 60fps interactions; efficient battery usage
  - Accessibility: Large tap targets; high‑contrast friendly colors
- Technology Preferences:
  - Language & Framework: Kotlin + Jetpack Compose (Material 3)
  - State & Architecture: Single‑Activity, MVVM with `ViewModel` and `State`
  - Persistence: Jetpack DataStore (Preferences) for simple durable counts
  - Haptics: `VibrationEffect`/`Vibrator` API with user‑respecting settings
  - Fonts: Bundle an Arabic font for the header (Noto Naskh Arabic) AND a rounded Latin font for numeric counters (Nunito) to achieve a consistent, beautiful look. Define separate `FontFamily` values (Arabic header vs. numeric counters). Prefer tabular/lining numerals for counters (OpenType feature `tnum`) when available. RTL support enabled.
  - Build: Gradle (KTS), latest stable Android Gradle Plugin and Kotlin
  - Icons: Material Icons; vector drawables
  - Permissions: None required (no Internet), optional `VIBRATE` if needed for haptics
- Architecture Considerations:
  - No network dependency; offline‑first
  - Repository layer wraps DataStore; UI observes via `StateFlow`
  - The Arabic header string stored as resource with `android:supportsRtl="true"`
  - Strictly single screen; navigation not required for MVP
  - Release: Signed APK/AAB with standard release config
- Security/Compliance:
  - No PII; no analytics or ads; no network access; follow Play policies

## Constraints & Assumptions
- Budget/Timeline: Hobby project; limited time; aim for quick iteration
- Resources: Single developer
- Technical: Android 12+ only; no server/backend; no cloud features
- Assumptions:
  - Users expect count to persist until explicit reset
  - Haptic feedback may be disabled by user/device and should be optional/respect settings

## Risks & Open Questions
- Font rendering and Arabic ligature quality across OEMs; mitigated by bundling a reliable font
- Tap target sizing and accidental taps across device sizes; mitigated with generous hit areas and visual feedback
- Persistence correctness during force‑close and process death; mitigated via atomic DataStore writes
- Open Questions:
  - Preferred bundled Arabic font (Noto Naskh vs Amiri)?
  - Exact color palette finalization (match prototype vs system dynamic color)?

## Next Steps
1. Initialize Android project (Kotlin, Compose Material 3, minSdk 31, target latest)
2. Implement UI matching the prototype: header, counter card, +1 primary button, −1, +10, +33, reset with confirm
3. Add state & logic: rolling 00–99, carry to Total Tasbeeh Completed, bulk add rollover
4. Implement DataStore persistence and restore on launch
5. Add optional haptic feedback on taps; respect system settings
6. QA on at least two Android 12+ devices/emulators; verify cold start and persistence
7. Prepare signed release build and export APK/AAB

---
PM Handoff: This brief is ready for implementation. Build the Android APK per MVP scope and Technical Considerations above.
