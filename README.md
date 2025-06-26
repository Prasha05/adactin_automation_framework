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
- ✅ **Jenkins** – Basic CI/CD setup completed for automated test runs.

## 📁 Project Structure

```
src/
├── main/
│   └── java/
│       └── com.adactinautomation/
│           ├── pageobjects/         ← POM classes (Page Object Model)
│           └── utilities/           ← Utilities: ConfigReader, LoggerHelper, ExcelUtils, etc.
│
├── test/
│   └── java/
│       └── com.adactinautomation.tests/   ← All TestNG test classes
│
└── resources/
    ├── ConfigFiles/
    │   └── config.properties        ← Configuration values (URL, browser, etc.)
    ├── Testdata/
    │   └── AdactIn_TestData.xlsx    ← Excel test data
    └── log4j2.xml                   ← Log4j configuration file
```

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
4. For Jenkins: Set up a job and use mvn clean test in the build step.

## Logging

- Logging is handled using **Log4j 2**.
- Logs are stored in the `logs/test-log.log` file.
- Console logging is currently disabled (can be enabled in `log4j2.xml`).

## Reporting

- **Extent Reports**: `test-output/ExtentReport.html`
- **TestNG Reports**: `test-output/index.html`

## Future Enhancements

- ✏️ Expand test coverage with more scenarios and validation logic.
- 🔍 Integrate email reporting and Slack alerts via Jenkins.

## Author

**Prasanth T**
[🔗 LinkedIn Profile](https://www.linkedin.com/in/prasanth-thanikachalam)

> This project is a self-learning initiative to demonstrate real-world test automation using best practices.
