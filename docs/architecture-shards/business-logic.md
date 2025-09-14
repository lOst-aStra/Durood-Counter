# 6) Business Logic (Rollover, Guards)

Rollover is defined on a base of 100.

- `increment1()`:
  - `current = (current + 1) % 100`
  - `if (current == 0) completed_sets += 1`
- `add10()` / `add33()`:
  - `sum = current + delta`
  - `completed_sets += sum / 100`
  - `current = sum % 100`
- `decrement()`:
  - If total (completed_sets*100 + current) == 0 → no-op
  - Else if current > 0 → `current -= 1`
  - Else (current == 0 and completed_sets > 0) → `completed_sets -= 1; current = 99`
- `reset()`:
  - Requires a two-step confirmation.
  - `current = 0; completed_sets = 0`

All mutations go through the repository with DataStore atomic updates.
