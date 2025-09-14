# Next Steps

## UX Expert Prompt
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

## Architect Prompt
Using this PRD as input, create a concise architecture prompt for an Android 12+ Kotlin + Jetpack Compose single-activity app using MVVM and DataStore persistence. Ensure offline-first assumptions, performance targets (≤1.5s cold start, 60fps), and typography choices (Noto Naskh Arabic, Nunito with tabular numerals) are respected. Emphasize testability and reliability across lifecycle/force-close scenarios.
