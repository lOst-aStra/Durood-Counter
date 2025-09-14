# Epic 4: Performance and UX Polish

## Epic Goal
Meet performance targets and elevate UX polish to ensure a smooth, reliable, and delightful experience on Android 12+ devices.

## Story 4.1 Optimize cold start performance
As a user,
I want the app to open quickly,
so that I can start counting without delay.

### Acceptance Criteria
1: Cold start ≤ 1.5s on a mid-tier Android 12 device
2: Measure with consistent methodology (e.g., Android Studio profiler) and document result
3: No functional regressions while achieving the target

## Story 4.2 60fps interaction and battery efficiency
As a user,
I want smooth interactions that feel responsive,
so that the app feels reliable and pleasant.

### Acceptance Criteria
1: No visible jank during rapid taps and state updates
2: Avoid unnecessary recompositions; confirm stability with tooling
3: No battery-draining loops or excessive wake-ups observed in basic profiling

## Story 4.3 Typography and legibility refinement
As a user,
I want consistently legible text and numerals,
so that I can read and interact comfortably.

### Acceptance Criteria
1: Arabic header uses Noto Naskh Arabic (verify ligature quality); RTL works throughout
2: Numeric counters use Nunito (or similar rounded Latin) with tabular numerals if available
3: Line-height, spacing, and contrast tuned for common device sizes

## Story 4.4 UI spacing, touch feedback, and micro-interactions
As a user,
I want clean spacing and clear feedback,
so that the interface feels calm and intentional.

### Acceptance Criteria
1: Spacing/hierarchy refined (cards, paddings, margins)
2: Touch states/haptics are consistent and not distracting
3: Visual polish does not regress accessibility targets (tap size, contrast)
