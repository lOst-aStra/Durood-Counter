# 10) Testing Strategy

Unit Tests (JVM):
- Rollover math: `increment1`, `add10`, `add33`, `decrement` with edge cases.
- Reset flow: ensure state becomes baseline after confirmation.

Instrumentation/UI Tests (androidTest):
- Compose UI tests for primary flows:
  - +1 increments display.
  - −1 guard at zero.
  - +10/+33 rollover into 100s sets.
  - Reset two-step confirmation.
- Persistence restore: write via repo, relaunch activity, assert state.

Test Utilities:
- Provide a fake in-memory repository or temporary DataStore for reliable tests.
- Use test tags for key nodes (e.g., `testTag("counter-value")`).
