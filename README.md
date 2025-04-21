# 🧪 Test Automation Framework

Это проект для автоматизации тестирования API и UI с использованием современных инструментов, таких как Karate, Playwright, и Allure.

## 💻 Technologies Used

- **Karate** – API Testing
- **Playwright (Java)** – UI Testing
- **JUnit5** – Test orchestration
- **Gradle** – Build & сборка проекта
- **Allure** – Тестовые отчёты
- **RestAssured + Gson** – Аутентификация API (caching токена)
- **Java** – Для кастомной логики и конфигурации  

---

## 🔐 Authentication

Authorization for API requests is done via a token, which is retrieved once and cached during runtime using:

```java
//BaseRestAuth.getAuthToken(); 
```


## 🚀 How to Run Tests

### 🧪 Karate API Tests

To execute all tests, run the following command in your terminal:

```bash
./gradlew clean test
```

To execute only Karate API Smoke tests, run:
```bash
./gradlew karateSmoke
```
To execute only UI Smoke tests, run:
```bash
./gradlew uiSmoke
```
To execute Smoke API + UI, run:
```bash
./gradlew smoke
```
To execute Regression API + UI, run:
```bash
./gradlew regress
```

## 🏷 Tagging Convention

The following tags are used to organize and execute tests effectively:
- **@Smoke** – Used for API smoke tests.
- **@SmokeUI** – Used for UI smoke tests.
- **@Sanity** – Used for quick sanity checks.
- **@Regression** – Used for the regression test suite.
- Example of manual run: ./gradlew -Dkarate.options="--tags @Smoke" karateSmoke

## 📊 Allure Report

After executing the tests, you can generate and open an Allure report:

```bash
./gradlew allureReport
./gradlew allureServe
```
