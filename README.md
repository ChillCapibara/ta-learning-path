# SDET Test Automation Framework

Reusable **test automation framework** built with **Java**. Current focus: **UI automation using Selenium + TestNG**, with **CI smoke execution in GitHub Actions**.  
API automation (**RestAssured**) is planned as the next module; mobile is a later stretch goal.

The framework separates **test intent from infrastructure**, enforces strong boundaries at compile time, and provides a foundation for scalable, maintainable automated testing.

---

## Key characteristics

- Clear separation between framework code and test scenarios
- `BasePage` as the single interaction API for Page Objects
- Internal helper layer encapsulating waits, retries, and JS fallbacks
- Thread-safe WebDriver lifecycle management
- Configuration-driven execution (browser, window mode, timeouts)
- **CI-ready smoke suite** (TestNG group `smoke` runs on PRs)
- Architecture designed to grow without structural refactors

---

## Project structure (high level)

```text
Module layout

framework-core/src/main/java/framework
├── config        // Configuration access (Config)
├── navigation    // URLs and endpoints (Urls, Endpoint)
├── model         // Domain models (User)
└── data          // Test data factories (Users)

framework-core/src/main/resources
└── config.properties

framework-web/src/main/java/framework
├── base          // BasePage + internal web helpers (ElementActions, WaitUtils, JsActions)
├── driver        // WebDriver lifecycle, factories, enums
└── pages         // Page Objects

framework-web/src/test/java
└── tests         // Test scenarios only (use TestNG groups like "smoke")

framework-web/src/test/resources
└── testng.xml    // TestNG suite configuration
```
---
## Tech stack

- **Language:** Java 17
- **UI automation:** Selenium WebDriver
- **Test framework:** TestNG
- **Build tool:** Maven
- **CI**: GitHub Actions

---

## Project goal

This project demonstrates **production-grade test framework design** from an SDET perspective, with emphasis on:

- maintainability
- correctness
- architectural clarity

The focus is on treating the test framework as **engineering software**, not a collection of test scripts.

---

## How to run
### Run all tests
``` mvn clean test ```

### Run UI smoke tests only
Smoke tests are tagged with TestNG group smoke.

```mvn -pl framework-web -am test -Dgroups=smoke```

## CI (GitHub Actions)

**Workflow**: `.github/workflows/ci.yml`

On every **push** and **pull request** to main, CI runs UI smoke tests only:

```mvn -q -U -pl framework-web -am test -Dgroups=smoke```

CI also sets `HEADLESS=true` for stable Linux runner execution.

## Test reports
Surefire reports are uploaded as GitHub Actions artifacts ```(**/target/surefire-reports/**)```.

## Scope and roadmap

Planned support:
- UI automation (implemented + CI smoke)
- API testing (RestAssured) — next module
- Mobile automation — later

The architecture isolates responsibilities to allow additional testing layers to be introduced without restructuring the existing framework.
