# 7) UI Composition (Compose M3)

Core composables and responsibilities:
- `DuroodScreen()` – top-level screen with Scaffold, padding, and content area
- `HeaderArabic()` – shows Arabic Durood Shareef text; uses Noto Naskh Arabic; RTL-aware
- `CounterCard()` – prominent numeric display using Nunito with tabular numerals
- `PrimaryButton()` – large +1 action (dominant visual weight)
- `ActionsRow()` – −1, +10, +33 horizontally with generous spacing and 48dp+ targets
- `ResetConfirmDialog()` – two-step confirmation before reset

Typography & Fonts:
- Bundle fonts in `app/src/main/res/font/`:
  - `noto_naskh_arabic_regular.ttf` → Arabic header `FontFamily`
  - `nunito_variable.ttf` (or fixed weights) with tabular numeral feature if available
- Define `Type.kt` to wire these families into `MaterialTheme(typography)`.

Colors & Layout:
- Calm, distraction-free scheme aligned with prototype; ensure high contrast.
- Rounded cards and clear elevation for `CounterCard`.

Haptics:
- Trigger subtle haptic feedback on primary actions where device/user settings permit.
- Centralize in `core/Haptics.kt` and call from ViewModel via one-shot UI event or callback.

RTL & Localization:
- `AndroidManifest.xml` → `android:supportsRtl="true"`.
- Header composable uses `LayoutDirection.Rtl` for Arabic content if needed.
