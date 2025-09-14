# Epic 2: Bulk Increments and Decrement Safety

## Epic Goal
Complete core counting workflows by adding bulk increments with correct rollover and safe decrement behavior, improving usability and accessibility.

## Story 2.1 Add +10 action with rollover
As a user,
I want a +10 button,
so that I can quickly add common sets aligned with my practice.

### Acceptance Criteria
1: Tapping +10 increases the current count by 10
2: Correct rollover into 100s (e.g., 95 + 10 → 05 and increment completed-sets counter if applicable)
3: Visual feedback on tap; performance remains smooth (no dropped frames)

## Story 2.2 Add +33 action with rollover
As a user,
I want a +33 button,
so that I can follow common dhikr increments efficiently.

### Acceptance Criteria
1: Tapping +33 increases the current count by 33
2: Correct rollover into 100s as above (carry logic mirrors +10)
3: Visual feedback on tap; respects haptics settings

## Story 2.3 Decrement with guard against negatives
As a user,
I want a −1 button that never produces a negative total,
so that I can safely correct mistakes without breaking the count.

### Acceptance Criteria
1: Tapping −1 reduces the count by 1 but never below 0
2: If using a “completed sets” counter, decrement logic never produces invalid state
3: Clear UI state when at zero (e.g., disabled/soft state for −1)

## Story 2.4 Accessibility and interaction refinements
As a user,
I want large, comfortable tap targets and clear visual feedback,
so that the app is easy and pleasant to use across devices.

### Acceptance Criteria
1: All interactive elements meet minimum touch target guidance
2: Contrast and feedback align with Accessibility goals
3: RTL header and numeric legibility remain intact
