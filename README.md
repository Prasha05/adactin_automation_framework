# Adactin Hotel Demo Automation Framework

## Overview

This project is a comprehensive automation testing framework built for the Adactin Hotel Demo website. It follows industry-standard best practices using **Selenium WebDriver**, **Java**, and the **Page Object Model (POM)**. The framework is structured using **Maven**, and integrates **TestNG** for test execution, **Apache POI** for data-driven testing, **Extent Reports** for reporting, and **Log4j 2** for detailed logging.

## Features

- ✅ **Selenium WebDriver** – For browser automation.
- ✅ **TestNG** – For structured test execution and assertions.
- ✅ **Maven** – For project management and dependencies.
- ✅ **Page Object Model (POM)** – For clean, reusable, and scalable code.
- ✅ **Apache POI** – For reading test data from Excel files.
- ✅ **Extent Reports** – For visual HTML test reporting.
- ✅ **Log4j 2** – For structured logging (file-based).
- 🔄 **Jenkins** – Planned for future CI/CD integration.

## Project Structure

- `src/test/resources/ConfigFiles` – Application configuration (`config.properties`)
- `src/test/resources/TestData` – Excel data files used in tests
- `src/test/resources/log4j2.xml` – Log4j 2 configuration
- `logs/` – Directory where log files are saved
- `test-output/ExtentReport.html` – Extent Reports after execution
- `test-output/` – TestNG default reports

## Prerequisites

- Java 17 (OpenJDK 17.0.13 or later)
- Maven
- TestNG (IDE plugin for Eclipse or IntelliJ)
- ChromeDriver / FirefoxDriver (handled via WebDriverManager)

## How to Execute

1. Clone this repository.
2. Update values in `config.properties` (browser, URL, credentials).
3. Run tests using:
   - `mvn clean test`
   - or through your IDE with TestNG

## Logging

- Logging is handled using **Log4j 2**.
- Logs are stored in the `logs/test-log.log` file.
- Console logging is currently disabled (can be enabled in `log4j2.xml`).

## Reporting

- **Extent Reports**: `test-output/ExtentReport.html`
- **TestNG Reports**: `test-output/index.html`

## Future Enhancements

- 🔄 CI/CD integration using Jenkins.
- ✏️ Expand test coverage with more scenarios and validation logic.

## Author

**Prasanth T**

> This project is a self-learning initiative to demonstrate real-world test automation using best practices.
