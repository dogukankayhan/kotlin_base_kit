# Pokemon App

This is a sample Android application that demonstrates modern Android development best practices.

## Features

*   View a list of Pokemons with pagination.
*   Search for Pokemons by name.
*   Filter Pokemons by type.
*   View details of a specific Pokemon.
*   Add and remove Pokemons from a list of favorites.

## Tech Stack

*   [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
*   [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android’s modern toolkit for building native UI.
*   [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and non-blocking programming.
*   [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
*   [Hilt](https://dagger.dev/hilt/) - For dependency injection.
*   [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
*   [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.
*   [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) - A software design philosophy that separates the elements of a design into ring levels.

## Setup

1.  Clone the repository:
    ```bash
    git clone https://github.com/your-username/pokemon-app.git
    ```
2.  Open the project in Android Studio.
3.  Build and run the application.

## Architecture

The application is structured based on Clean Architecture principles, with the following modules:

*   `:app`: The main application module that integrates all other modules.
*   `:core:common`: Contains common utilities and extensions.
*   `:core:data`: Implements the data layer, including repositories and data sources.
*   `:core:domain`: Contains the business logic of the application, including use cases and repository interfaces.
*   `:core:model`: Contains the data models used throughout the application.
*   `:core:navigation`: Defines the navigation routes and arguments.
*   `:core:network`: Handles network requests using Retrofit.
*   `:core:ui`: Contains common UI components.
*   `:feature:pokemons`: Implements the Pokemon list and detail screens.
*   `:feature:favorites`: Implements the favorites screen.

## SOLID Principles

The project adheres to the SOLID principles of object-oriented design:

*   **Single Responsibility Principle:** Each class and function has a single, well-defined responsibility.
*   **Open/Closed Principle:** Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification.
*   **Liskov Substitution Principle:** Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.
*   **Interface Segregation Principle:** No client should be forced to depend on methods it does not use.
*   **Dependency Inversion Principle:** High-level modules should not depend on low-level modules. Both should depend on abstractions.
