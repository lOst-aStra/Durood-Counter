# 3) High-Level Architecture

```
+-----------------------------+
|         UI Layer            |  Jetpack Compose M3
|  - DuroodScreen()           |  - Arabic header (RTL)
|  - CounterCard()            |  - Primary +1, −1, +10, +33, Reset
+--------------▲--------------+
               | State (immutable)
+--------------▼--------------+
|       ViewModel (MVVM)      |
|  - CounterViewModel         |
|  - Exposes StateFlow<State> |
|  - Business logic:          |
|    increment1/add10/add33   |
|    decrement/reset with 2-step
+--------------▲--------------+
               | Repository API
+--------------▼--------------+
|        Data Layer            |
|  - CounterRepository        |
|  - DataStore<Preferences>   |
|  - Atomic read/update       |
+-----------------------------+
```
