# Epic List

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
