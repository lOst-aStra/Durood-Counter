# Epic 1: Foundation & Core Counter

## Epic Goal
Establish a usable, reliable counter app foundation with +1 action, persistence, Arabic header, and basic theming/haptics.

## Story 1.1 Initialize Android app skeleton
As a developer,
I want a Kotlin + Compose M3 single-activity project scaffold,
so that I can implement the core counter UI quickly and cleanly.

### Acceptance Criteria
1: Project uses Kotlin + Compose M3 with Single-Activity + MVVM setup
2: App builds and runs on Android 12+ device/emulator
3: Basic theme and typography applied

## Story 1.2 Counter UI with +1 action
As a user,
I want a large +1 button to increment the counter,
so that I can recite and track counts easily.

### Acceptance Criteria
1: Large, centered +1 button increments visible counter
2: Counter is legible with rounded numeric font (e.g., Nunito; tabular numerals if available)
3: Arabic Durood header visible with RTL support

## Story 1.3 Persist counts with DataStore
As a user,
I want my count to persist across restarts,
so that I don’t lose progress until I reset intentionally.

### Acceptance Criteria
1: Count restored on app launch after process death/restart
2: DataStore used for persistence with atomic writes
3: No network/analytics dependencies introduced

## Story 1.4 Haptic feedback and basic polish
As a user,
I want subtle haptic feedback on primary actions,
so that interactions feel responsive without being distracting.

### Acceptance Criteria
1: Haptics on +1 tap (respect device/user settings)
2: Visual feedback on press
3: No regressions in performance targets for this epic
