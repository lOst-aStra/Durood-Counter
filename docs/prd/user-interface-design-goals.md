# User Interface Design Goals

## Overall UX Vision
Minimal, distraction-free single-screen experience focused on dhikr. Immediate readiness: app opens directly to the counter with a large +1 primary action. Calm, spiritual aesthetic with Arabic Durood header (RTL) and clear, legible typography. Zero ads, zero distractions, and no onboarding blockers.

## Key Interaction Paradigms
- Single primary action (+1) as a large centered button.
- Quick-add actions: +10 and +33 with rollover to 100s sets.
- Safe decrement (−1) with guard against negative totals.
- Two-step confirmation for reset to prevent accidental loss.
- Persistent counts across restarts until explicit reset.
- Generous hit areas and high-contrast friendly colors.
- Optional haptic feedback that respects device/user settings.
- RTL-aware layout for the Arabic header; numerals remain consistently legible.
- No navigation flow; one main screen for MVP.

## Core Screens and Views
- Login Screen: Not applicable (MVP single-screen app).
- Main Dashboard: Single counter screen with Arabic header, counter card, +1 primary button, −1, +10, +33, reset with confirmation.
- Item Detail Page: Not applicable.
- Settings Page: Not required for MVP; consider later for haptic toggle or theme if needed.

## Accessibility
- Target practical alignment with WCAG AA for contrast and tap size.
- Large tap targets for all interactive elements.
- High-contrast friendly color options/themes.
- Clear feedback on taps/haptics.

## Branding
- Clean, calm palette aligned to prototype.
- Arabic header font: Noto Naskh Arabic for high-quality ligatures.
- Numeric counters: rounded Latin font (e.g., Nunito); prefer tabular/lining numerals (tnum) for stable alignment.
- Rounded cards and Material 3 elevation/shadows for visual clarity.

## Target Device and Platforms
- Android phones only.
- Android 12+ (API 31+).
- Offline-first; no network access required.
