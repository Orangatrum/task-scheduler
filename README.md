# Modular Task Scheduler & Priority Queue Engine

A decoupled, Java-based Task Scheduling Engine built with a Priority Queue and JSON file persistence. Designed following clean architecture principles (Model-Engine-UI-Storage).

## Features
- **Priority Queue Processing:** Dynamically orders tasks based on priority (`HIGH`, `MEDIUM`, `LOW`) and due date.
- **Persistent Storage:** Saves and loads state seamlessly using JSON via Google Gson.
- **Clean Architecture:** Strict separation of concerns across `model`, `engine`, `storage`, and `ui` packages.
- **Interactive CLI:** Input-validated command-line interface built in Java 17+.

## Tech Stack
- **Language:** Java 17+
- **Build Tool:** Apache Maven
- **Dependencies:** Google Gson (2.10.1)

## Architecture Overview
- `model`: Encapsulates `Task` domain objects and `Priority` enums implementing `Comparable`.
- `engine`: Manages core business logic and `PriorityQueue` operations independently of UI.
- `storage`: Handles JSON serialization/deserialization with custom `LocalDate` adapters.
- `ui`: Handles user interaction, input parsing, and error catching.

## How to Run

### Prerequisites
- JDK 17 or higher
- Apache Maven

### Build and Run
1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/task-scheduler-engine.git](https://github.com/your-username/task-scheduler-engine.git)
