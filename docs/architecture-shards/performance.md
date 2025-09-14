# 9) Performance Considerations

- Cold start ≤ 1.5s:
  - Avoid heavy work in `Application` or `Activity#onCreate`.
  - Lazy-load fonts and avoid excessive recomposition.
  - Keep dependency graph minimal; no reflection-heavy DI.
- Rendering @ 60fps:
  - Keep state minimal; avoid large recompositions by scoping state.
  - Use `remember` and stable data classes; avoid lambdas capturing mutable vars.
- DataStore:
  - Small payload; atomic updates; avoid write amplification (batch operations where feasible).
- Optional later: Baseline Profiles to improve cold start on release builds.
