# Kotlin Base Project

A modern, modularized Android starter project built with Kotlin and Jetpack Compose, following Clean Architecture principles and best practices.

## 🚀 Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material 3.
- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/).
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/).
- **Database**: [Room](https://developer.android.com/training/data-storage/room).
- **Asynchronous Work**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html).
- **Navigation**: [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/navigation#hilt-navigation).
- **Static Analysis**: [Detekt](https://detekt.dev/) & [Ktlint](https://pinterest.github.io/ktlint/).
- **Local Storage**: [DataStore](https://developer.android.com/topic/libraries/architecture/datastore).

## 🏗️ Architecture

The project follows a highly modular architecture based on **Clean Architecture** principles to ensure scalability, testability, and maintainability.

### Module Structure

- **`:app`**: The entry point of the application. Handles navigation initialization (`NavHost`) and dependency injection setup.
- **`:feature:*`**: Business features of the app (e.g., `:feature:auth`, `:feature:favorites`). Each module is self-contained.
- **`:core:*`**: Essential building blocks shared across the app:
  - `:core:ui`: Common UI components, themes, and base classes.
  - `:core:network`: Network infrastructure, interceptors, and API definitions.
  - `:core:data`: Repositories and local data sources.
  - `:core:domain`: Use cases and domain models.
  - `:core:common`: Utilities, validators, and language management.
  - `:core:navigation`: Centralized navigation and DeepLink handling.

### Key Patterns

- **Coordinator Pattern**: Used for navigation logic decoupling within feature modules.
- **MVI/MVVM (BaseViewModel)**: A structured approach to state management using `UiState`, `UiEvent`, and `UiEffect`.
- **Validation System**: A rule-based validation framework located in `:core:common`.

## 🛠️ Getting Started

### Prerequisites

- Android Studio Iguana (2023.2.1) or newer.
- JDK 17.
- Kotlin 1.9.22.

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/dogukankayhan/kotlin_base_kit.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle and build the project.

## 🤝 Coding Standards

To maintain code quality, the project uses `Ktlint` and `Detekt`.

- **Format Code**:
  ```bash
  ./gradlew ktlintFormat
  ```
- **Run Linter**:
  ```bash
  ./gradlew ktlintCheck detekt
  ```

## 📬 Contact

Project maintained by [Dogukan Kayhan](https://github.com/dogukankayhan).
