<a id="readme-top"></a>

<h1 align="center">
  🦁
  <br>
  Zoo Management System
  <br>
</h1>

<p align="center">
	<b>An object-oriented zoo management application built in Java, demonstrating advanced OOP principles, generics, and exception handling.</b>
</p>
<p align="center">
	<a href="https://github.com/Royzalah/java-oop-zoo-management/issues">Report Bug</a>
	- 	<a href="https://github.com/Royzalah/java-oop-zoo-management/issues">Request Feature</a>
	- 	<a href="#5-demonstration">View Demo</a>
</p>

---

  <p align="center">
    <img src="https://img.shields.io/badge/Java-17%2B-orange?style=flat&logo=java&logoColor=white" alt="Java" />
    <img src="https://img.shields.io/badge/Spring_Boot-Backend-6DB33F?style=flat&logo=springboot&logoColor=white" alt="Spring Boot" />
    <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=flat&logo=apachemaven&logoColor=white" alt="Maven" />
    <img src="https://img.shields.io/badge/Paradigm-OOP-blue?style=flat" alt="OOP" />
    <img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License" />
  </p>


## 1 About The Project

**Zoo Management System** is a comprehensive object-oriented application that simulates the management of a virtual zoo. The system handles multiple animal families, supports a generic veterinary clinic, persists state to a binary file, and exposes a clean API to a web-based front-end via the `IZoo` interface. It demonstrates the full power of Java's OOP toolkit — inheritance, polymorphism, generics, custom exceptions, and serialization — through a practical, evolving domain.

The project was developed as the final project for the **Object-Oriented Programming and Java (10128)** course in my Computer Science BSc at Afeka Tel Aviv Academic College of Engineering.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## 2 Key Features

- **Three Animal Families**: Predators (Lions, Tigers), Penguins, and Fish (Aquarium Fish, Goldfish, Clownfish) — each with type-specific behavior.
- **Polymorphic Behavior**: Every animal implements `feed()`, `makeNoise()`, and `ageOneYear()` with its own logic.
- **Penguin Leadership System**: One penguin is designated leader; when removed, the next-tallest is automatically promoted.
- **Multi-criteria Sorting**: Sort penguins by height (`Comparable`), or by name and age using custom `Comparator` classes.
- **Generic Veterinary Clinic**: `VetClinic<T extends Animal>` provides type-safe medical treatment management.
- **Lifecycle Simulation**: Animals age, lose happiness over time, and are automatically removed at end-of-life.
- **Binary Persistence**: Full zoo state is saved to `zoo.data` on exit and restored on startup.
- **Robust Input Validation**: Custom `GeneralException` hierarchy validates every input with type-specific rules.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## 3 Architecture

The project follows a modular OOP architecture with strict separation between the controller, models, utilities, and exceptions:

```
com.example.zoo.ass3/
├── manage/                # Controller layer — bridge between UI and Zoo
│   └── Manage.java        # Implements IZoo interface
├── models/                # Domain entities and aggregates
│   ├── Animal.java        # Abstract base class
│   ├── Predator.java      # Lions and Tigers
│   ├── Penguin.java       # With leadership system
│   ├── Fish.java          # Base fish class with subtypes
│   ├── Zoo.java           # Main aggregate root
│   ├── VetClinic.java     # Generic veterinary clinic
│   └── MedicalTreatment.java
├── general/               # Shared utilities and enums
│   ├── enums/             # FishTypes, PredatorsTypes, Colors, Patterns
│   ├── Address.java
│   ├── FoodSummary.java
│   └── DataUtils.java
└── exceptions/            # Custom exception hierarchy
    └── GeneralException.java
```

The `Manage` class implements `IZoo` and serves as the **single entry point** for all front-end requests, delegating business logic to the `Zoo` aggregate.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## 4 Classes Overview

Below is a table detailing the **key components** and their roles.

| Class Name           | Layer/Type           | Description                                                              |
| :------------------- | :------------------- | :----------------------------------------------------------------------- |
| `Manage`             | **Controller**       | Implements `IZoo`. Bridges the front-end with the `Zoo` aggregate.       |
| `Zoo`                | **Aggregate Root**   | Manages all animals, handles business logic, persistence, and the clinic.|
| `Animal`             | **Abstract Model**   | Base class for all animals. Defines `feed()`, `makeNoise()`, `ageOneYear()`. |
| `Penguin`            | **Model**            | Implements `Comparable<Penguin>` for height-based sorting; supports leader logic. |
| `Predator`           | **Model**            | Base class for Lions and Tigers with weight-based feeding logic.         |
| `Fish`               | **Model**            | Base class for fish hierarchy with color/pattern attributes.             |
| `VetClinic<T>`       | **Generic Class**    | Type-safe treatment manager for any subtype of `Animal`.                 |
| `MedicalTreatment`   | **Value Object**     | Represents a single medical treatment with description and date.         |
| `GeneralException`   | **Custom Exception** | Root of the validation exception hierarchy.                              |

### 🧠 OOP Principles Demonstrated

| Principle              | Implementation                                                       |
| :--------------------- | :------------------------------------------------------------------- |
| **Inheritance**        | `Animal` → `Predator` / `Penguin` / `Fish` hierarchies               |
| **Abstraction**        | Abstract base classes define contracts for subclasses                |
| **Polymorphism**       | `feed()`, `makeNoise()`, `ageOneYear()` overridden per species       |
| **Encapsulation**      | Private fields with controlled getter/setter access                  |
| **Generics**           | `VetClinic<T extends Animal>`, typed `Map` and `List` collections    |
| **Interfaces**         | `IZoo`, `Comparable<Penguin>`, `Serializable`                        |
| **Exception Handling** | Custom `GeneralException` hierarchy with try-with-resources          |
| **Serialization**      | Binary persistence via `ObjectOutputStream` / `ObjectInputStream`    |
| **Custom Comparators** | Sort penguins by name and age using dedicated `Comparator` classes   |

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## 5 Demonstration

> *Screenshots and demo video will be added soon. To see the full UI, run the project locally and open `http://localhost:8080`.*

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## 6 Getting Started

To get a local copy up and running, follow these steps.

### 6.1 Prerequisites

*   **Java 17+** installed
    *   Verify by running `java -version` in your terminal
*   **IntelliJ IDEA** (recommended) — https://www.jetbrains.com/idea/
*   No Node.js / npm required
*   Internet connection (first run only — Maven downloads dependencies)

### 6.2 Installation
1. Clone the repo
```bash
git clone https://github.com/Royzalah/java-oop-zoo-management.git
```

2. Open the project in **IntelliJ IDEA**
    *   File → Open → select the cloned folder
    *   IntelliJ will automatically detect the Maven project and download dependencies

3. Run the application using one of these methods:
    *   **From IntelliJ**: Run the `ZooApplication` main class
    *   **Windows**: Double-click `run.bat`
    *   **Mac/Linux**: `chmod +x run.sh && ./run.sh`

4. Open your browser at **http://localhost:8080** — the UI will load

### 6.3 Verifying the Server

Check that the API is alive by visiting:
```
http://localhost:8080/api/health
```
You should receive:
```json
{"status":"ok"}
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## 7 Usage

**How to Use:**
*   **Browse the zoo**: Click *Show zoo details* to see all animals grouped by family.
*   **Manage animals**: Add new animals via *Add animal*, feed them all with *Feed all animals*, or hear their sounds with *Show sound all animals*.
*   **Sort penguins**: Use *Show penguins* and switch between sorting by height, name, or age.
*   **Age the zoo**: Click *Show increasing all animal age by One year* to advance time — animals will age, lose happiness, and may be removed.
*   **Veterinary clinic**: Click *Show Veterinary Clinic* to view animals receiving medical treatment.
*   **Save & exit**: Click *Exit* to persist all data to `zoo.data` for the next session.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## 8 Acknowledgments

The **web server template and UI** (Spring Boot wrapper, HTML/CSS/JS frontend, REST controllers) were provided by the course instructor **Pini Shlomi** as the application shell that all students built their backend against.

The **OOP backend** (`Manage`, `Zoo`, all animal models, exceptions, custom comparators, generic veterinary clinic) was written by me as the assignment solution, implementing the `IZoo` interface defined by the wrapper.

### Course Information
*   **Course**: Object-Oriented Programming and Java
*   **Institution**: Afeka Tel Aviv Academic College of Engineering


<p align="right">(<a href="#readme-top">back to top</a>)</p>


## 9 Author

<div align="center">
 <b>Roei Zalah</b>
 <br>
 CS Student @ Afeka College
 <br><br>
 <a href="https://github.com/Royzalah">
   <img src="https://img.shields.io/badge/GitHub-Royzalah-181717?style=for-the-badge&logo=github" alt="GitHub" />
 </a>
 <a href="https://linkedin.com/in/roeizalah">
   <img src="https://img.shields.io/badge/LinkedIn-roeizalah-0A66C2?style=for-the-badge&logo=linkedin" alt="LinkedIn" />
 </a>
</div>

<p align="right">(<a href="#readme-top">back to top</a>)</p>
