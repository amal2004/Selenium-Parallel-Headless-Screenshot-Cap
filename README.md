# 🚀 Selenium Parallel Automation Framework with Headless and Screen Capture Support (Java + TestNG)

A **robust, scalable, and thread-safe Selenium automation framework** built using:

* Java
* Selenium WebDriver
* TestNG
* WebDriverManager

Designed with **parallel execution**, **clean architecture**, and **real-world best practices**.

---

## 📌 Key Features

* ⚡ Parallel execution using TestNG
* 🧵 Thread-safe WebDriver with `ThreadLocal`
* 🌐 Cross-browser support (Chrome, Firefox, Edge)
* ⚙️ External configuration via `config.properties`
* 🧪 Page Object Model (POM) architecture
* 📸 Automatic screenshot capture (failure & skip)
* 🔄 Data-driven testing with DataProvider
* 🎧 TestNG Listener integration
* 🧼 Clean driver lifecycle management
* ❗ Strong exception handling

---

## 🏗️ Project Structure

```
com.amalw
│
├── base
│   └── BaseTest.java
│
├── config
│   └── ConfigManager.java
│
├── driver
│   ├── DriverFactory.java
│   └── BrowserManager.java
│
├── enums
│   └── BrowserType.java
│
├── exceptions
│   └── FrameworkException.java
│
├── listeners
│   └── TestFailureListener.java
│
├── pages
│   ├── BasePage.java
│   └── RegisterPage.java
│
├── tests
│   └── RegistrationTest.java
│
└── utils
    └── ScreenshotManager.java
```

---

## ⚙️ Configuration

### 📄 `config.properties`

```
base.url=http://localhost:5000
browser=firefox
timeout=30
headless=true
screenshot.dir=./screenshots
```

---

### 🔁 Override via Command Line

```
mvn test -Dbrowser=chrome -Dheadless=false
```

> System properties (`-Dkey=value`) override config file values.

---

## 🧠 Framework Architecture

This framework follows **Page Object Model (POM)** with clear separation of concerns.

---

### 🔹 ConfigManager

* Loads config from classpath (`/config.properties`)
* Supports system overrides
* Provides:

  * `get()`
  * `getInt()`
  * `getBoolean()`
* Fails fast using `FrameworkException`

---

### 🔹 BrowserManager

* Creates WebDriver instances
* Uses WebDriverManager (initialized once via static block)
* Supports:

  * Chrome
  * Firefox
  * Edge
* Handles optimized **headless mode arguments**

---

### 🔹 DriverFactory

* Thread-safe driver management using `ThreadLocal`
* Delegates driver creation to `BrowserManager`
* Applies:

  * Page load timeout
  * Zero implicit wait
  * Conditional maximize (non-headless)

---

### 🔹 BasePage

Reusable Selenium actions:

* click()
* type()
* getText()
* navigateTo()
* wait utilities (explicit waits only)

---

### 🔹 RegisterPage

* Encapsulates registration page actions
* Uses config-driven URL:

```
base.url + "/register"
```

---

### 🔹 ScreenshotManager

* Captures screenshots with:

  * Timestamp
  * Test class grouping
* Stores under:

```
/screenshots/<TestClass>/
```

---

### 🔹 TestFailureListener

* Automatically captures screenshots on:

  * ❌ Test Failure
  * ⚠️ Test Skip
* Uses thread-safe WebDriver instance

---

### 🔹 BaseTest

* Initializes driver before test
* Quits driver after execution
* Keeps test classes clean

---

## 🔄 Execution Flow

```
TestNG Test
   ↓
BaseTest
   ↓
DriverFactory (ThreadLocal)
   ↓
BrowserManager (Driver Creation)
   ↓
BasePage
   ↓
Page Classes
   ↓
Selenium WebDriver
```

---

## 🧪 Test Execution

### Parallel Execution

* Enabled via TestNG
* Thread-safe execution using `ThreadLocal`
* DataProvider supports parallel runs

---

## 🧾 Sample Test Flow

1. Initialize WebDriver
2. Navigate to application
3. Perform UI actions
4. Submit form
5. Validate result
6. Capture screenshot (on failure/skip)

---

## 🧪 Example Test

```java
@Test(dataProvider = "registrationData")
public void testRegistration(...) {

    RegisterPage page = new RegisterPage();

    page.open();
    page.selectGender(gender);
    page.fillForm(...);
    page.submit();

    Assert.assertTrue(page.isRegistrationSuccessful());
}
```

---

## 🚀 Running the Framework

### 📥 Clone Repository

```
git clone https://github.com/your-repo/selenium-parallel.git
```

---

### ▶️ Run Tests

```
mvn clean test
```

---

### 🌐 Run on Specific Browser

```
mvn test -Dbrowser=edge
```

---

### 🧪 Run in Headless Mode

```
mvn test -Dheadless=true
```

---

## 📸 Screenshot Example

```
screenshots/
 └── RegistrationTest/
     └── testRegistration_20260101_101010.png
```

---

## 🧰 Tech Stack

* Java 17+
* Selenium WebDriver 4+
* TestNG
* Maven
* WebDriverManager

---

## 🔥 Future Enhancements

* Allure / Extent Reports
* CI/CD (GitHub Actions / Jenkins)
* Docker execution support
* Retry mechanism for flaky tests
* API + UI hybrid framework

---

## 👨‍💻 Author

Designed for building scalable, maintainable, and parallel Selenium automation frameworks.
