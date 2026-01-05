# SDET Test Automation Framework

Reusable **test automation framework** built with **Java**, designed to support **UI, API, and mobile testing**, with an initial focus on **UI automation using Selenium and TestNG**.

The framework separates **test intent from infrastructure**, enforces strong boundaries at compile time, and provides a solid foundation for scalable, maintainable automated testing.

---

## Key characteristics

- Clear separation between framework code and test scenarios
- `BasePage` as the single interaction API for Page Objects
- Internal helper layer encapsulating waits, retries, and JS fallbacks
- Thread-safe WebDriver lifecycle management
- Configuration-driven execution (browser, window mode, timeouts)
- Architecture designed to grow without structural refactors

---

## Project structure (high level)

```text
src/main/java/framework
├── base          // BasePage, BaseTest, internal helpers
├── driver        // WebDriver lifecycle, factories, enums
├── config        // Configuration access
├── navigation    // URLs and endpoints
├── pages         // Page Objects
├── model         // Domain models
├── data          // Test data factories

src/test/java
└── tests         // Test scenarios only
```
---
## Tech stack

- **Language:** Java 17
- **UI automation:** Selenium WebDriver
- **Test framework:** TestNG
- **Build tool:** Maven

---

## Project goal

This project demonstrates **production-grade test framework design** from an SDET perspective, with emphasis on:

- maintainability
- correctness
- architectural clarity

The focus is on treating the test framework as **engineering software**, not a collection of test scripts.

---

## Scope and roadmap

The framework is designed as a unified automation platform with planned support for:

- **UI automation** (current focus)
- **API testing** (RestAssured)
- **Mobile automation** (TBD)

The architecture intentionally isolates responsibilities to allow additional testing layers to be introduced **without restructuring the existing framework**.

---

## Status

- Core framework structure stabilized
- UI automation layer implemented
- API and mobile layers planned

Next steps include:
- reporting and screenshots
- logging strategy
- parallel execution
- CI integration
