# Android Minimalist Template
A minimalist template with networking, Compose, and Room database.

## Stack

**Network:** Retrofit (API), Coil (images)

**UI:** Jetpack Compose, Material 3

**Data:** Room (local database), Kotlin Flow (reactive), Hilt (DI)

**Tests**: UI tests with [Maestro](https://maestro.mobile.dev/). See [HappyFlow.yaml](app/src/maestro/HappyFlow.yaml).

## Philosophy

### Peripheral Vision

Quoting Paul Graham in [Great Hackers](https://paulgraham.com/gh.html),

> John McPhee wrote that Bill Bradley's success as a basketball player was due partly to his extraordinary peripheral vision. "Perfect" eyesight means about 47 degrees of vertical peripheral vision. Bill Bradley had 70; he could see the basket when he was looking at the floor. Maybe great hackers have some similar inborn ability. (I cheat by using a very dense language, which shrinks the court.)

The cheat here is to keep the code denser. There should be no tunnel of 'clean code', no chain of factories, but rather putting related code closer to each other.

### AI Ready

Related to the peripheral vision, you should be able to paste a file and related interfaces into GPT and ask it to add features. A file should not have too many implementation details. If AI can't navigate it easily, humans will have trouble too.

### Minimalist

Everything in the stack should improve peripheral vision. Each addition should be questioned on whether it improves or takes away from the codebase's clarity and maintainability.
