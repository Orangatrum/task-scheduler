# High-Performance Task Scheduler (REST API & CLI)

A production-ready, multithreaded Task Scheduler application built with **Java 17**, **Spring Boot**, and **JUnit 5**. The core engine utilizes a customized `PriorityQueue` to dynamically schedule tasks based on dual-tiered sorting logic (priority levels and due dates). 

This project features both a RESTful Web Service interface and an interactive Command-Line Interface (CLI), with JSON file persistence.

---

## 🏗️ Architecture & Key Technical Decisions

The application follows a modular five-layer architecture (`model`, `engine`, `storage`, `controller`, `ui`) to maintain a strict separation of concerns:

- **Engine (`com.taskscheduler.engine`)**: Contains `TaskScheduler.java`, powered by Java's `PriorityQueue` ($O(\log N)$ insertion and removal). Implements customized `Comparable<Task>` logic to prioritize tasks by `Priority` level first (HIGH > MEDIUM > LOW), defaulting to earliest `dueDate` upon priority collision.
- **Controller (`com.taskscheduler.controller`)**: Exposes RESTful endpoints via `@RestController` for seamless web integration and external API requests.
- **Storage (`com.taskscheduler.storage`)**: Uses **Google Gson** with custom `LocalDate` serializers/deserializers for reliable JSON serialization and filesystem persistence (`tasks.json`).
- **UI (`com.taskscheduler.ui`)**: Provides a CLI interface for standalone local execution alongside the web engine.

---

## 🚀 Tech Stack

- **Language:** Java 17+
- **Framework:** Spring Boot 3.x (Spring Web, Embedded Tomcat)
- **Data Structures:** Custom-ordered Priority Queue, Lists
- **Serialization:** Google Gson
- **Testing:** JUnit 5
- **Build Tool:** Maven

---

## 🔌 REST API Reference

### Base URL: `http://localhost:8080/api/tasks`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/tasks` | Fetch all scheduled tasks in the queue |
| `POST` | `/api/tasks` | Add a new task to the priority queue |
| `GET` | `/api/tasks/top` | Peek at the highest priority task without dequeuing |
| `DELETE` | `/api/tasks/top` | Dequeue (complete) the highest priority task |

#### Example Request: Create a Task (`POST /api/tasks`)

```json
{
  "id": "1a2b3c4d",
  "description": "Prepare for Technical Interview",
  "priority": "HIGH",
  "dueDate": "2026-09-15"
}

## How to Run

### Prerequisites
- JDK 17 or higher
- Apache Maven

### Build and Run
1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/task-scheduler-engine.git](https://github.com/your-username/task-scheduler-engine.git)
