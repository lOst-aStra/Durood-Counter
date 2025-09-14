# 5) Data Model & Persistence

Data is small, numeric, and session-oriented. Use DataStore (Preferences) for durability and atomic updates.

Preferences schema (keys):
- `current_count: Int` – 0..99 (rolling set window)
- `completed_sets: Int` – number of full 100s completed
- `last_updated_epoch_ms: Long` – optional; for future audit/UX

DataStore specifics:
- File name: `counter_prefs.pb` (though using Preferences, not Proto; name is arbitrary)
- Reads: single flow returning a small state DTO for repository mapping.
- Writes: `updateData { prefs -> ... }` ensuring atomicity.
- Threading: Default DataStore dispatcher; repository exposes suspend functions + cold flows.
