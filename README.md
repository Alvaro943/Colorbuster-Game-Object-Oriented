# Color Buster

Color Buster is a tile-matching game developed in Java using Swing. It was created as an object-oriented programming project, focusing on GUI development, modular design, and design patterns.

## Features

- Configurable board size and tile colors.
- Optional countdown timer.
- Score tracking and persistent high scores.
- Hint system and pause/resume functionality.
- Multiple screens for menus, gameplay, and game over.

## Technologies

- Java
- Java Swing
- IntelliJ IDEA

## Design Patterns and Architecture

- **Model–View–Controller (MVC):** Separates game logic, interface components, and user interaction handling.
- **Singleton:** Used by `GameController` and `GameView` to provide a single shared instance of each class.
- **Observer:** A listener-based mechanism allows the timer to notify registered components of time updates.
- **Event-Driven Programming:** Swing listeners handle user actions and coordinate gameplay.

## Getting Started

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Ensure `src` is configured as a source root.
4. Run `Main.java`.

## Project Goal

Apply object-oriented programming principles and design patterns while developing an interactive Java desktop game.
