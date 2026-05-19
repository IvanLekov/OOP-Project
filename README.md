# Dungeons & Dragons - Text RPG

A simple, text-based rogue-like RPG console game developed as a university course project. The application demonstrates core Object-Oriented Programming (OOP) concepts and SOLID design principles in Java.

## Features

- **Command-Line Interface (CLI):** Fully interactive text engine driving game states (Menu, Exploration, Combat, Level Up).
- **Procedural Levels:** 4 carefully balanced levels with dynamic layout-preserving monster and treasure spawns.
- **Combat System:** Strategic turn-based combat tracking health, strength, and mana for both the hero and monsters.
- **Progression & Stats:** Allocate stat points upon reaching a level's exit to customize your hero's build.
- **Equipment & Items:** Discover multi-tier items (Weapons, Spells, Armor) from treasures that scale across dungeon levels.
- **Save & Load System:** XML-based game storage that completely serializes game context and world layouts (persists dead monsters and gathered loot).

## Package Structure

The project is structured under the following base package:
`bg.tu_varna.sit.f24621660.dnd`

Key components include:
- `.cli`: Driving the input parsing and game engine.
- `.core`: Managing game context, states, and dependency injection.
- `.entities`: Base factories and classes for heroes and monsters.
- `.io`: Handlers for XML file storage and level map loading.
- `.world`: Map management, cell interactions, and coordinate positions.

## How to Run

1. Open the project in your preferred IDE (e.g., IntelliJ IDEA).
2. Ensure you have Java JDK 17 or higher configured.
3. Locate the `Application.java` class in `src/bg/tu_varna/sit/f24621660/dnd/`.
4. Run the `main` method to launch the game in the console.
