# Epic 3: Session Management and Reset

## Epic Goal
Provide intentional session control and resilience: reliable persistence across restarts/force-closes and a clear two-step reset flow, plus polish.

## Story 3.1 Two-step confirmation reset
As a user,
I want a clear two-step confirmation before resetting,
so that I don’t accidentally lose my session.

### Acceptance Criteria
1: Reset flow requires explicit confirmation (e.g., dialog with clear messaging)
2: After confirmation, both current count (and completed-sets counter if present) reset to baseline
3: Visual state reflects reset immediately; focus returns to primary action

## Story 3.2 Robust persistence across lifecycle and force-close
As a user,
I want my counts to be safe across device restarts and app force-closes,
so that I don’t lose progress unexpectedly.

### Acceptance Criteria
1: DataStore writes are atomic and consistent during rapid taps and app backgrounding
2: Verified restore after process death and force-close relaunch paths
3: No data corruption or stale state on resume

## Story 3.3 Reliability validation and edge-case handling
As a PM/QA,
I want validation against common edge cases,
so that reliability targets are met without analytics or tracking.

### Acceptance Criteria
1: Define and verify a checklist for edge cases (e.g., rapid +1 spam, reset during write, low-memory kill)
2: In-app debug build logging behind a flag (no PII, no network)
3: Meets persistence reliability target (≥ 99% across force-closes/restarts) in test runs

## Story 3.4 UI polish and affordances
As a user,
I want consistent polish that reinforces clarity and intent,
so that the app feels trustworthy and calm.

### Acceptance Criteria
1: Reset affordance is visible but not distracting; spacing and hierarchy refined
2: Clear empty/zero state visuals; no ambiguous states after reset
3: Micro-interactions align with performance and accessibility targets
