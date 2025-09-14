# 8) ViewModel & State

`CounterViewModel` exposes:
- `state: StateFlow<CounterState>` with fields:
  - `current: Int`
  - `completedSets: Int`
  - `isResetConfirmVisible: Boolean`
  - `hapticsAvailable: Boolean` (optional, cached from system)

Intents:
- `onIncrement()`, `onDecrement()`, `onAdd10()`, `onAdd33()`
- `onRequestReset()`, `onConfirmReset()`, `onDismissReset()`

Lifecycle:
- Collect repository flow in init and map to `CounterState`.
- Use `viewModelScope` for suspend operations.
