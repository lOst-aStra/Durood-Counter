# Requirements

## Functional Requirements
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

## Non-Functional Requirements
- NFR1: Cold start time ≤ 1.5s on a mid-tier Android 12 device.
- NFR2: Crash-free sessions ≥ 99.5%.
- NFR3: Tap accuracy on +1 ≥ 98% on common devices.
- NFR4: Accessibility: large tap targets and high-contrast friendly color scheme.
- NFR5: Platform: Android phones, min OS Android 12 (API 31+).
- NFR6: Security/Privacy: No PII, no network access, compliant with Play policies.
- NFR7: Persistence reliability ≥ 99% across force-closes and restarts.
- NFR8: Smooth interaction at 60fps; efficient battery usage.
- NFR9: UI legibility for Arabic header and numeric counters across common devices.
